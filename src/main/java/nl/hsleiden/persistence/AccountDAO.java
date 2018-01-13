package nl.hsleiden.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.xml.transform.Result;

import com.google.inject.Inject;
import nl.hsleiden.HttpResponse;
import nl.hsleiden.model.Account;

import nl.hsleiden.service.DatabaseService;

@Singleton
public class AccountDAO {
	private PreparedStatement createStatement;
	private PreparedStatement getAccountByIdStatement;
	private PreparedStatement getAllAccountsStatement;
	private  PreparedStatement deleteAccountStatement;
	private PreparedStatement authenticateStatement;
	private PreparedStatement getAccountWithoutPassword;
	private PreparedStatement updateStatement;
	private PreparedStatement authenticateById;

	public Connection dbConnection;
	@Inject
	public AccountDAO(DatabaseService databaseService) {
		this.dbConnection = databaseService.getConnection();
		prepareStatements();

	}

	private void prepareStatements(){
		try{
			createStatement = dbConnection.prepareStatement("INSERT INTO account(acc_voornaam, acc_tussenvoegsel, acc_achternaam, acc_email, acc_wachtwoord, acc_straat, acc_postcode, acc_huisnr, acc_woonplaats ) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)");
			getAccountByIdStatement = dbConnection.prepareStatement("SELECT * FROM account WHERE acc_id = ?");
			getAllAccountsStatement = dbConnection.prepareStatement("SELECT * FROM account");
			getAccountWithoutPassword = dbConnection.prepareStatement("SELECT * FROM account WHERE acc_email = ?");
			authenticateStatement = dbConnection.prepareStatement("SELECT acc_id, acc_voornaam, acc_tussenvoegsel, acc_achternaam, acc_email FROM account WHERE acc_email = ? AND acc_wachtwoord = ?");
			updateStatement = dbConnection.prepareStatement("UPDATE account SET acc_voornaam = ?, acc_tussenvoegsel = ?, acc_achternaam = ?, acc_email = ?, acc_straat = ?, acc_postcode = ?, acc_huisnr = ?, acc_woonplaats = ? WHERE acc_id = ? ");
			authenticateById = dbConnection.prepareStatement("SELECT acc_id FROM account WHERE acc_id = ? AND acc_wachtwoord = ?");
			deleteAccountStatement  = dbConnection.prepareStatement("DELETE FROM account WHERE acc_id = ?");
			}
		catch(SQLException e){
			System.out.println("Error in the Prepare Statements (in AccountDao" + e.getStackTrace());
		}
	}

	public HttpResponse createAccount(Account account){
		try {
			createStatement.setString(1, account.getFirstname());
			createStatement.setString(2, account.getPrefix());
			createStatement.setString(3,account.getLastname());
			createStatement.setString(4,account.geteMail());
			createStatement.setString(5, account.getPassword());
			createStatement.setString(6,  account.getStreet());
			createStatement.setString(7,  account.getZipCode());
			createStatement.setString(8,  account.getHouseNumber());
			createStatement.setString(9,  account.getTown());

			createStatement.executeUpdate();
			return new HttpResponse(Response.Status.OK, "Account succesvol toegevoegd");
		} catch (SQLException e) {
			e.printStackTrace();
			return new HttpResponse(Response.Status.INTERNAL_SERVER_ERROR, "Er is iets mis gegaan bij het aanmaken van een account");
			//System.out.println("Error in the CreateAccount (in AccountDao)");
		}
	}

	public HttpResponse updateAccount(Account account){
		try {
			authenticateById.setInt(1, account.getId());
			authenticateById.setString(2, account.getPassword());
			ResultSet resultSet = authenticateById.executeQuery();
			if(!resultSet.next()){
				return new HttpResponse(Response.Status.FORBIDDEN, "Uw opgegeven wachtwoord komt niet overeen...");
			}

			updateStatement.setString(1, account.getFirstname());
			updateStatement.setString(2, account.getPrefix());
			updateStatement.setString(3,account.getLastname());
			updateStatement.setString(4,account.geteMail());
			updateStatement.setString(5,  account.getStreet());
			updateStatement.setString(6,  account.getZipCode());
			updateStatement.setString(7,  account.getHouseNumber());
			updateStatement.setString(8,  account.getTown());
			updateStatement.setInt(9, account.getId());

			updateStatement.executeUpdate();
			return new HttpResponse(Response.Status.OK, "Account succesvol bijgewerkt");
		} catch (SQLException e) {
			e.printStackTrace();
			return new HttpResponse(Response.Status.INTERNAL_SERVER_ERROR, "Er is iets mis gegaan bij het updaten van een account");
			//System.out.println("Error in the update (in AccountDao)");
		}

	}


	public Account getAccountById(int id){
		try{
			getAccountByIdStatement.setInt(1, id);

			ResultSet resultSet = getAccountByIdStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}

			Account account = makeAccount(resultSet);

			return account;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}




	public boolean authenticateAccount(String username, String password){
		try {

			authenticateStatement.setString(1, username);
			authenticateStatement.setString(2, password);

			ResultSet authenicate = authenticateStatement.executeQuery();

			if(authenicate.next()){
				return true;
			} else {
				return false;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Account getAccountByUsername(String username){

		try {

			getAccountWithoutPassword.setString(1, username);

			ResultSet resultSet = getAccountWithoutPassword.executeQuery();

			if (!resultSet.next()) {
				return null;
			}


			Account account = makeAccount(resultSet);
			return account;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account makeAccount(ResultSet resultSet) throws SQLException{
		Account account = new Account();
		account.setFirstname(resultSet.getString("acc_voornaam"));
		account.setPrefix(resultSet.getString("acc_tussenvoegsel"));
		account.setLastname(resultSet.getString("acc_achternaam"));
		account.seteMail(resultSet.getString("acc_email"));
		account.setStreet(resultSet.getString("acc_straat"));
		account.setZipCode(resultSet.getString("acc_postcode"));
		account.setHouseNumber(resultSet.getString("acc_huisnr"));
		account.setTown(resultSet.getString("acc_woonplaats"));
		account.setId(resultSet.getInt("acc_id"));
		account.setAdmin(resultSet.getBoolean("acc_isadmin"));
		return account;
	}

	public HttpResponse deleteAccount(int accountid){
		try {
			deleteAccountStatement.setInt(1, accountid);
			deleteAccountStatement.executeUpdate();
			return new HttpResponse(Response.Status.OK, "Account succesvol verwijderd");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new HttpResponse(Response.Status.INTERNAL_SERVER_ERROR, "Er is iets mis gegaan bij het verwijderen van het account");
	}

	public ArrayList<Account> getAllAccounts(){
		try {
			ResultSet resultSet = getAllAccountsStatement.executeQuery();
			ArrayList<Account> accounts = new ArrayList();

			while(resultSet.next()){
				Account account = makeAccount(resultSet);
				accounts.add(account);
			}
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
