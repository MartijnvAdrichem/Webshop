package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.security.auth.Subject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.Principal;

/**
 * Documentatie...
 *
 * @author Guus
 * @since 1.0, 6-12-2017
 */
public class Account implements Principal{

	@JsonProperty
	int id;

	@JsonProperty
	@NotNull(message = "  is verplicht")
	@Length(min = 1,max = 30,message = "  mag niet meer dan 30 characters zijn")
	String firstname;

	@JsonProperty
	@NotNull(message = "  is verplicht")
	@Length(min = 1,max = 30,message = "  mag niet meer dan 30 characters zijn")
	String lastname;

	@JsonProperty
	@Length(max = 20,message = "  mag niet meer dan 20 characters zijn")
	String prefix;

	@JsonProperty
	@NotNull(message = "  is verplicht")
	@Length(min = 3,max = 50,message = "  mag niet minder dan 3 of meer dan 255 characters zijn")
	String username;

	@JsonProperty
	@NotNull(message = "  is verplicht")
	@Length(min = 5, max = 255, message = "  moet minimaal 5 en maximaal 255 characters lang zijn")
	String password;

	@JsonProperty
	Boolean isActive;

	@JsonProperty
	Boolean isAdmin;


	@JsonProperty
	Boolean inactiveProducts;
	@JsonProperty
	Boolean inactiveClients;

	public Account(){

	}

	public Account(String firstname, String lasstname, String prefix, String username, String password, Boolean isAdmin, Boolean isActive, Boolean inactiveClients, Boolean inactiveProducts){
		this.firstname = firstname;
		this.lastname = lasstname;
		this.prefix = prefix;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isActive = isActive;
		this.inactiveClients = inactiveClients;
		this.inactiveProducts = inactiveProducts;
	}
	public Account(int id, String firstname, String lasstname, String prefix, String username, String password, Boolean isAdmin){
		this.id = id;
		this.firstname = firstname;
		this.lastname = lasstname;
		this.prefix = prefix;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	public Boolean getInactiveProducts() {
		return inactiveProducts;
	}

	public void setInactiveProducts(Boolean inactiveProducts) {
		this.inactiveProducts = inactiveProducts;
	}

	public Boolean getInactiveClients() {
		return inactiveClients;
	}

	public void setInactiveClients(Boolean inactiveClients) {
		this.inactiveClients = inactiveClients;
	}

	@Override
	public String getName() {
		return username;
	}

	public boolean equals(Account account) {
		return username.equals(account.getUsername());
	}


}
