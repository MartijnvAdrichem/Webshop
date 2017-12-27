/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.service;

import java.util.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;

import javax.inject.Inject;
import javax.inject.Singleton;

import nl.hsleiden.model.Account;
import org.jose4j.jwt.consumer.JwtContext;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class AuthenticationService implements Authenticator<JwtContext, Account>, Authorizer<Account>
{
    private final AccountService accountService;
    
    @Inject
    public AuthenticationService(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @Override
    public Optional<Account> authenticate(JwtContext context) throws AuthenticationException {
            final long userId = (long) context.getJwtClaims().getClaimValue("userID");
            int userIdInt = Math.toIntExact(userId);
            Account account = accountService.getAccountById(userIdInt);
            if (account != null) {
                return Optional.of(accountService.getAccountById(userIdInt));
            }
            return Optional.empty();
    }



    @Override
    public boolean authorize(Account user, String roleName) {
		if(user == null){
			return false;
		}

		return true;
    }
}
