package nl.hsleiden.resource;

import com.google.common.base.Throwables;
import com.google.inject.Singleton;
import nl.hsleiden.ApiConfiguration;
import nl.hsleiden.model.Account;
import nl.hsleiden.model.Credentials;
import nl.hsleiden.service.AccountService;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import static java.util.Collections.singletonMap;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

@Singleton
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource
{
    private final AccountService accountService;
    private final ApiConfiguration configuration;

    @Inject
    public AuthResource(AccountService service, ApiConfiguration configuration) {
        this.accountService = service;
        this.configuration = configuration;
    }

    @POST
    @Path("/login")
    public Response login(Credentials credentials) {

        if (accountService.attemptToLogin(credentials.getUsername(), credentials.getPassword())) {
            Account account = accountService.getAccountByUsername(credentials.getUsername());
            return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(generateValidToken(account.getId()))
                    .build();
        }
        //Else return forbidden
        return Response.status(Response.Status.FORBIDDEN)
                .type(MediaType.APPLICATION_JSON)
                .entity(singletonMap("message", "invalid credentials"))
                .build();
    }

    private Map<String, String> generateValidToken(int userId) {
        final JwtClaims claims = new JwtClaims();
        byte[] tokenSecret = new byte[0];
        try {
            tokenSecret = configuration.getJwtTokenSecret();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        claims.setSubject("Authenticated user");
        claims.setClaim("userID", userId);
        claims.setExpirationTimeMinutesInTheFuture(240);

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenSecret));

        try {
            return singletonMap("token", jws.getCompactSerialization());
        }
        catch (JoseException e) { throw Throwables.propagate(e); }
    }
}

