package dev.husein.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import dev.husein.util.PasswordHandler;

@Entity
@Table(name = "COMPANY_USER")
public class User {
	
	public User() {	
	}
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "NAME")
	@NotBlank
	private String name;
	
	@Column(name = "EMAIL")
	@NotBlank
	private String email;
	
	@Column(name = "PASSWORD")
	@NotBlank
	private String password;
	
	@Column(name = "GENDER")
	@NotBlank
	private String gender;
	
	@Column(name = "BIRTH_DATE")
	private String dateOfBirth;
	
	@Column(name = "ADDRESS_LINE")
	private String addressLine;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "CONTACT")
	private String contact;
	
	@Column(name = "REGISTRATION_DATE")
	private String registerationDate;
	
	@Column(name = "AUTHORIZED")
	private String authorized;
	
	public User(String name, String email, String password, String gender,
			String dateOfBirth, String addressLine, String city, String state, String country, String contact,
			String registerationDate, String authorized) {
		this.name = name;
		this.email = email;
		this.password = PasswordHandler.hashPassword(password);
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.country = country;
		this.contact = contact;
		this.registerationDate = registerationDate;
		this.authorized = authorized;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return PasswordHandler.hashPassword(password);
	}

	public void setPassword(String password) {
		this.password = PasswordHandler.hashPassword(password);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRegisterationDate() {
		return registerationDate;
	}

	public void setRegisterationDate(String registerationDate) {
		this.registerationDate = registerationDate;
	}

	public String getAuthorized() {
		return authorized;
	}

	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", dateOfBirth=" + dateOfBirth + ", addressLine=" + addressLine + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", contact=" + contact + ", registerationDate="
				+ registerationDate + ", authorized=" + authorized + "]";
	}
	
}
