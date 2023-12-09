package com.nks.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.nks.admin.model.NewsSubmitEntity;

@Repository
public interface NewsSubmitRepo extends JpaRepository<NewsSubmitEntity, Long> {
	
	//@Query("UPDATE NewsSubmitEntity n SET articleID = :articleID ")
	//public void updateStoryById(@Param("articleID") String articleID);

}
