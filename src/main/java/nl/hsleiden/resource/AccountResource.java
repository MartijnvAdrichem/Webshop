package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
import nl.hsleiden.View;
import nl.hsleiden.model.Account;
import nl.hsleiden.service.AccountService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author Guus Stouten
 */
@Singleton
@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource
{
	private final AccountService service;


	@Inject
	public AccountResource(AccountService service) {
		this.service = service;

	}

	@POST
	@Path("create")
	public Response createAccount(@Valid Account account) {
		try {
			return service.createAccount(account).send();
		} catch(javax.validation.ValidationException e){
			ValidationExceptionMapper validationExceptionMapper = new ValidationExceptionMapper();
			return validationExceptionMapper.toResponse(e);
		}
	}

	@GET
	@Path("all")
	@JsonView(View.Public.class)
	public ArrayList<Account> getAccounts() {
		return service.getAllAccounts();
	}

	@GET
	@Path("me")
	public Account getAuthenticatedAccount(@Auth Account account){
		System.out.println(account.getAdmin());
		return account;
	}
	@PUT
	@Path("edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editAccount(@Valid Account account) {
		try{
			return service.updateAccount(account).send();
		} catch(javax.validation.ValidationException e){
			ValidationExceptionMapper validationExceptionMapper = new ValidationExceptionMapper();
			return validationExceptionMapper.toResponse(e);
		}
	}

	@PUT
	@Path("settings")
	public void updateSettings(Account account){
		service.updateSettings(account);
	}

	@GET
	@Path("transfer/all")
	public ArrayList<Account> getAllAccountsForTransfer(){
		ArrayList<Account> accounts = service.getAllAcountsForTransfer();
		System.out.println("Accounts zijn " + accounts.size());
		return accounts;
	}
}