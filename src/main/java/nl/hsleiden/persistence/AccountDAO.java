package nl.hsleiden.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import nl.hsleiden.HttpResponse;
import nl.hsleiden.model.Account;

import nl.hsleiden.service.DatabaseService;

/**
 *
 * @author Guus Stouten
 */
@Singleton
public class AccountDAO
{
	private PreparedStatement createStatement;
	private PreparedStatement getAccountByIdStatement;
	private PreparedStatement selectAllAccountsStatement;
	private PreparedStatement authenticateStatement;
	private PreparedStatement getGebruikerZonderWachtwoord;
	private PreparedStatement updateStatement;

	public DatabaseService databaseService;
	@Inject
	public AccountDAO(DatabaseService databaseService) {
		this.databaseService = databaseService;
		prepareStatements();

	}

	private void prepareStatements(){
		try{
			createStatement = databaseService.getConnection().prepareStatement("INSERT INTO account(acc_voornaam, acc_tussenvoegsel, acc_achternaam, acc_email, acc_wachtwoord, acc_straat, acc_postcode, acc_huisnr, acc_woonplaats ) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)");
			getAccountByIdStatement = databaseService.getConnection().prepareStatement("SELECT * FROM account WHERE acc_id = ?");
			selectAllAccountsStatement = databaseService.getConnection().prepareStatement("SELECT * FROM account");
			getGebruikerZonderWachtwoord = databaseService.getConnection().prepareStatement("SELECT acc_id, acc_voornaam, acc_tussenvoegsel, acc_achternaam, acc_email  FROM account WHERE acc_email = ?");
			authenticateStatement = databaseService.getConnection().prepareStatement("SELECT acc_id, acc_voornaam, acc_tussenvoegsel, acc_achternaam, acc_email FROM account WHERE acc_email = ? AND acc_wachtwoord = ?");
			updateStatement = databaseService.getConnection().prepareStatement("UPDATE account SET acc_voornaam = ?, acc_tussenvoegsel = ?, acc_achternaam = ?, acc_email = ?, acc_wachtwoord = ?, acc_straat = ?, acc_postcode = ?, acc_huisnr = ?, acc_woonplaats = ? WHERE acc_id = ? ");
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

/*	public HttpResponse updateAccount(Account account){
		try {
			updateStatement.setString(1, account.getFirstname());
			updateStatement.setString(2, account.getPrefix());
			updateStatement.setString(3,account.getLastname());
			updateStatement.setString(4,account.getUsername());
			updateStatement.setString(5, account.getPassword());
			updateStatement.setBoolean(6,  account.getAdmin());
			updateStatement.setBoolean(7   , account.getActive());
			updateStatement.setInt(8, account.getId());

			updateStatement.executeUpdate();
			return new HttpResponse(Response.Status.OK, "Account succesvol bijgewerkt");
		} catch (SQLException e) {
			e.printStackTrace();
			return new HttpResponse(Response.Status.INTERNAL_SERVER_ERROR, "Er is iets mis gegaan bij het updaten van een account");
			//System.out.println("Error in the update (in AccountDao)");
		}

	}
*/

	public Account getAccountById(int id){
		try{
			getAccountByIdStatement.setInt(1, id);

			ResultSet resultSet = getAccountByIdStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}

			Account account = new Account();
			account.setFirstname(resultSet.getString("acc_voornaam"));
			account.setPrefix(resultSet.getString("acc_tussenvoegsel"));
			account.setLastname(resultSet.getString("acc_achternaam"));
			account.setId(resultSet.getInt("acc_id"));

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

			getGebruikerZonderWachtwoord.setString(1, username);

			ResultSet resultSet = getGebruikerZonderWachtwoord.executeQuery();

			if (!resultSet.next()) {
				return null;
			}


			Account account = new Account();
			account.setFirstname(resultSet.getString("acc_voornaam"));
			account.setPrefix(resultSet.getString("acc_tussenvoegsel"));
			account.setLastname(resultSet.getString("acc_achternaam"));
			account.setId(resultSet.getInt("acc_id"));
			return account;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account makeAccount(ResultSet rs){
		try {
			Account account = new Account();
			account.setId(rs.getInt("acc_id"));
			account.setFirstname(rs.getString("acc_voornaam"));
			account.setLastname(rs.getString("acc_achternaam"));
			account.setPrefix(rs.getString("acc_tussenvoegsel"));
			account.setPassword(rs.getString("acc_wachtwoord"));

			return account;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
