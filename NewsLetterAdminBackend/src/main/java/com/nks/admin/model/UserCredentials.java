package com.nks.admin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_credentials")
public class UserCredentials implements Cloneable {

	@Id
	@Column(name = "user_id", nullable = false)
	private final String emailId;

	@Column(name = "user_password", nullable = false)
	private final String userPassword;

	@Column(name = "user_role", nullable = false)
	private final String userRole;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "user_id")
	private UserDetailsEntity userDetails;

	protected UserCredentials() {
		this.emailId = "";
		this.userPassword = "";
		this.userRole = "";
		// this.userDetails = null;
	}

	public UserCredentials(String emailId, String userPassword, String userRole) {
		this.emailId = emailId;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}

	public void addUserDetails(UserDetailsEntity user) throws CloneNotSupportedException {
		this.userDetails = (UserDetailsEntity)user.clone();
	}

	public String getEmailId() {
		return emailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public UserDetailsEntity getUserDetails() throws CloneNotSupportedException {
		return (UserDetailsEntity) userDetails.clone();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
