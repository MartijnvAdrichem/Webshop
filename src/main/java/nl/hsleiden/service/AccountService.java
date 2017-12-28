package nl.hsleiden.service;

import nl.hsleiden.HttpResponse;
import nl.hsleiden.model.Account;
import nl.hsleiden.persistence.AccountDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;


@Singleton
public class AccountService {

	private final AccountDAO accountDAO;

	@Inject
	public AccountService(AccountDAO accountDAO) {

		this.accountDAO = accountDAO;
	}

	public HttpResponse createAccount(Account account) {
		return accountDAO.createAccount(account);
	}

	//public HttpResponse updateAccount(Account account) {
		//return accountDAO.updateAccount(account);
	//}

	public Account getAccountById(int id) {

		return(accountDAO.getAccountById(id));
	}

	public Boolean attemptToLogin(String username, String password){
		boolean succesfull = accountDAO.authenticateAccount(username,password);
		return succesfull;
	}

	public Account getAccountByUsername(String username){
		return accountDAO.getAccountByUsername(username);
	}

}
