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
	@Length(min = 5, max = 255, message = "  moet minimaal 5 en maximaal 255 characters lang zijn")
	String password;

	@JsonProperty
	@NotNull(message = "  is verplicht")
	@Length(min = 5, max = 50, message = "  moet minimaal 5 en maximaal 100 characters lang zijn")
	String eMail;

	@JsonProperty
	@NotNull(message = "  is verplicht")
	@Length(min = 5, max = 255, message = "  moet minimaal 5 en maximaal 50 characters lang zijn")
	String street;

	@JsonProperty
	@NotNull(message = "  is verplicht")
	@Length(min = 1, max = 4, message = "  moet minimaal 1 en maximaal 4 characters lang zijn")
	String houseNumber;

	@JsonProperty
	@NotNull(message = "  is verplicht")
	@Length(min = 6, max = 6, message = "  moet 6 karakters zijn (format 1234AA)")
	String zipCode;

	@JsonProperty
	@NotNull(message = "  is verplicht")
	@Length(min = 1, max = 255, message = "  moet minimaal 1 en maximaal 80 characters lang zijn")
	String town;



	public Account(){

	}

	public Account(String firstname, String lasstname, String prefix,  String password){
		this.firstname = firstname;
		this.lastname = lasstname;
		this.prefix = prefix;
		this.password = password;
	}
	public Account(int id, String firstname, String lasstname, String prefix,  String password){
		this.id = id;
		this.firstname = firstname;
		this.lastname = lasstname;
		this.prefix = prefix;
		this.password = password;
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


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}


	@Override
	public String getName() {
		return null;
	}
}
