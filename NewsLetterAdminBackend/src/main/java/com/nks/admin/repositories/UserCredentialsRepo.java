package com.nks.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nks.admin.model.UserCredentials;

@Repository
public interface UserCredentialsRepo extends JpaRepository<UserCredentials, String> {
	public UserCredentials findByEmailId(String username);
}
