package com.nks.identity.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nks.identity.service.entity.UserCredentials;

public interface UserCredRepo extends JpaRepository<UserCredentials, String> {

	Optional<UserCredentials> findByEmailId(String username);

}
