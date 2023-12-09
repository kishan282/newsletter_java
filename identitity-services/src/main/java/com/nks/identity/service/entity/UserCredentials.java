package com.nks.identity.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_credentials")
public class UserCredentials {
	
	@Id
	@Column(name = "userId")
	private String emailId;

	@Column
	private String userPassword;

	@Column
	private String userRole;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
