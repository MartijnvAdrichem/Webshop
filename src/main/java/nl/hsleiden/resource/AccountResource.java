package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
import nl.hsleiden.View;
import nl.hsleiden.model.Account;
import nl.hsleiden.service.AccountService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


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
	@Path("register")
	public Response createAccount(@Valid Account account) {
		try {
			return service.createAccount(account).send();
		} catch(javax.validation.ValidationException e){
			ModelValidator modelValidator = new ModelValidator();
			return modelValidator.toResponse(e);
		}
	}


	@GET
	@Path("me")
	public Account getAuthenticatedAccount(@Auth Account account){
		return account;
	}

	@PUT
	@Path("edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editAccount(@Valid Account account) {
		try{
			return service.updateAccount(account).send();
		} catch(javax.validation.ValidationException e){
			ModelValidator modelValidator = new ModelValidator();
			return modelValidator.toResponse(e);
		}
	}

	@GET
	@Path("all")
	@RolesAllowed("Admin")
	public ArrayList<Account> getAllAccounts(){
		return service.getAllAccounts();
	}

	@DELETE
	@Path("delete/{id}")
	@RolesAllowed("Admin")
	public Response deleteAccount(@PathParam("id") int id){
		return service.deleteAccount(id).send();
}

}