package com.nks.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class GetUserDetails {

	@JsonIgnore
	private final String firstName;
	@JsonIgnore
	private final String lastName;
	private final String fullName;
	private final String emailId;
	private final String role;

	public GetUserDetails(String firstName, String lastName, String emailId, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = (lastName == null || lastName.isEmpty()) ? firstName : firstName + " " + lastName;
		this.emailId = emailId;
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getRole() {
		return role;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getFullName() {
		return fullName;
	}

}
