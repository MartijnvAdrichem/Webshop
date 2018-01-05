
package nl.hsleiden;

import com.google.inject.Module;
import com.hubspot.dropwizard.guice.GuiceBundle.Builder;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.UnsupportedEncodingException;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import nl.hsleiden.filter.ClientFilter;
import nl.hsleiden.filter.JwtAuthFilter;
import nl.hsleiden.model.Account;
import nl.hsleiden.service.AuthenticationService;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiApplication extends Application<ApiConfiguration>
{
    private final Logger logger = LoggerFactory.getLogger(ApiApplication.class);
    private ConfiguredBundle assetsBundle;
    private GuiceBundle guiceBundle;
    private String name;

    @Override
    public String getName()
    {
        return name;
    }
    
    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap)
    {
        assetsBundle = (ConfiguredBundle) new ConfiguredAssetsBundle("/assets/", "/client", "index.html");
        guiceBundle = createGuiceBundle(ApiConfiguration.class, new ApiGuiceModule());
        
        bootstrap.addBundle(assetsBundle);
        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(new MultiPartBundle());
    }
    
    @Override
    public void run(ApiConfiguration configuration, Environment environment)
    {
        name = configuration.getApiName();
        logger.info(String.format("Set API name to %s", name));
        try {
            setupAuthentication(configuration, environment);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString());
        }
        configureClientFilter(environment);
        configureCors(environment);
    }
    
    private GuiceBundle createGuiceBundle(Class<ApiConfiguration> configurationClass, Module module)
    {
        Builder guiceBuilder = GuiceBundle.<ApiConfiguration>newBuilder()
                .addModule(module)
                .enableAutoConfig(new String[] { "nl.hsleiden" })
                .setConfigClass(configurationClass);

        return guiceBuilder.build();
    }
    
    private void setupAuthentication(ApiConfiguration configuration, Environment environment) throws UnsupportedEncodingException {
        AuthenticationService authenticationService = guiceBundle.getInjector().getInstance(AuthenticationService.class);
        ApiUnauthorizedHandler unauthorizedHandler = guiceBundle.getInjector().getInstance(ApiUnauthorizedHandler.class);

        final byte[] key = configuration.getJwtTokenSecret();

        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance


        environment.jersey().register(new AuthDynamicFeature(
                new JwtAuthFilter.Builder<Account>()
                        .setJwtConsumer(consumer)
                        .setRealm("realm")
                        .setPrefix("Bearer")
                        .setAuthenticator(authenticationService)
                        .setAuthorizer(authenticationService)
                        .setUnauthorizedHandler(unauthorizedHandler)
                        .buildAuthFilter()));
        
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Account.class));
    }
    
    private void configureClientFilter(Environment environment)
    {
        environment.getApplicationContext().addFilter(
            new FilterHolder(new ClientFilter()),
            "/*",
            EnumSet.allOf(DispatcherType.class)
        );
    }

    private void configureCors(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*martijnvanadrichem.nl");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }
    
    public static void main(String[] args) throws Exception
    {
        new ApiApplication().run(args);
    }
}
