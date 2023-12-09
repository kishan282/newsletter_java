package com.nks.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_details")
public class UserDetailsEntity implements Cloneable {

	@Column
	private final String firstName;

	@Column
	private final String lastName;

	@Id
	@Column(name = "user_id", nullable = false)
	private final String emailId;

	@Column
	private final String userAddress;

	@Column
	private final String userState;

	@Column
	private final String userZipcode;

	@Column
	private final String userContact;

	@Column
	@Lob
	@JsonIgnore
	private final byte[] userImage;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "userDetails")
	@PrimaryKeyJoinColumn
	private final UserCredentials userCreds;

	/*
	 * protected UserDetailsEntity() { this.emailId = ""; this.firstName = "";
	 * this.lastName = ""; this.userAddress = ""; this.userContact = "";
	 * this.userImage = null; this.userState = ""; this.userZipcode = ""; }
	 */
	
	public UserDetailsEntity() {
		this.firstName = "";
		this.lastName = "";
		this.emailId = "";
		this.userAddress = "";
		this.userState = "";
		this.userZipcode = "";
		this.userImage = null;
		this.userContact = "";
		this.userCreds = null;
	}

	private UserDetailsEntity(String firstName, String lastName, String emailId, String userAddress, String userState,
			String userZipcode, byte[] userImage, String userContact, UserCredentials userCreds) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.userAddress = userAddress;
		this.userState = userState;
		this.userZipcode = userZipcode;
		this.userImage = userImage.clone();
		this.userContact = userContact;
		this.userCreds = userCreds;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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

	public byte[] getUserImage() {
		return userImage.clone();
	}

	public UserCredentials getUserCreds() throws CloneNotSupportedException {
		return (UserCredentials) userCreds.clone();
	}

	public String getEmailId() {
		return emailId;
	}

	public static class UserBuilder {

		private final String firstName;
		private final String lastName;
		private final String emailId;
		private final String userAddress;
		private final String userState;
		private final String userZipcode;
		private final String userContact;
		private byte[] userImage;
		private final UserCredentials userCreds;

		private UserBuilder(String firstName, String lastName, String emailId, String userAddress, String userState,
				String userZipCode, String userContact, UserCredentials creds) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.emailId = emailId;
			this.userAddress = userAddress;
			this.userState = userState;
			this.userZipcode = userZipCode;
			this.userContact = userContact;
			this.userCreds = creds;
		}

		public UserBuilder addUserImage(byte[] image) {
			this.userImage = image.clone();
			return this;
		}

		public UserDetailsEntity build() {
			return new UserDetailsEntity(firstName, lastName, emailId, userAddress, userState, userZipcode, userImage,
					userContact, userCreds);
		}

	}

	public static UserBuilder builder(String firstName, String lastName, String emailId, String userAddress,
			String userState, String userZipCode, String userContact, UserCredentials userCreds) {
		return new UserBuilder(firstName, lastName, emailId, userAddress, userState, userZipCode, userContact,
				userCreds);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
