package com.nks.admin.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AddAdminOrEditorRequest {

	@NotBlank(message = "Firstname is mandatory field")
	private final String firstName;
	
	private final String lastName;
	
	@NotBlank(message = "EmailID is mandatory field")
	@Pattern(regexp = "^\\S+@(?i)(gmail|outlook|microsoft).com$", message = "'Entered invalid emailID'")
	private final String emailId;
	
	@NotBlank(message = "Password is mandatory field")
	@Size(min = 5, message = "Password must be more than 5 characters")
	@Pattern(regexp = "[A-Z]+\\S+\\d+\\W+", message = "Password must contains min one UpperCase letter numeric digit and special character")
	private final String userPassword;
	
	@NotBlank(message = "'Address is mandatory field'")
	private final String userAddress;
	
	@NotBlank(message = "'State is mandatory field'")
	private final String userState;
	
	@NotBlank(message = "'ZipCode is mandatory field'")
	@Size(max = 6, min = 6, message = "Invalid zip code")
	private final String userZipcode;
	
	@NotBlank(message = "'Contact is mandatory field'")
	@Size(max = 10, min = 10, message = "Invalid phone number")
	private final String userContact;
	
	@NotBlank(message = "'Role is mandatory field'")
	private final String userRole;

	@JsonCreator
	public AddAdminOrEditorRequest(@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName, @JsonProperty("emailId") String emailId,
			@JsonProperty("userPassword") String userPassword, @JsonProperty("userAddress") String userAddress,
			@JsonProperty("userState") String userState, @JsonProperty("userZipcode") String userZipcode,
			@JsonProperty("userContact") String userContact, @JsonProperty("userRole") String userRole) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
		this.userState = userState;
		this.userZipcode = userZipcode;
		this.userContact = userContact;
		this.userRole = userRole;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public String getUserState() {
		return userState;
	}

	public String getUserZipcode() {
		return userZipcode;
	}

	public String getUserContact() {
		return userContact;
	}

	public String getUserRole() {
		return userRole;
	}

}
