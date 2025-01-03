package com.nks.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nks.admin.model.NewsImageEntity;

@Repository
public interface NewsImageRepo extends JpaRepository<NewsImageEntity, Long> {

}