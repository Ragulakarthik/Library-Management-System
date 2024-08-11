package com.lms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.Publisher;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher ,Long>{
	
}
