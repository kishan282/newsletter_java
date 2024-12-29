package com.nks.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nks.admin.model.UserDetailsEntity;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetailsEntity, String> {
	public UserDetailsEntity findByEmailId(String username);
	public void deleteByEmailId(String username);
}
