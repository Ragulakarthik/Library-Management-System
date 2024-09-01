package com.lms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lms.entity.Publisher;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {

    @Query("SELECT p FROM Publisher p WHERE p.publisherName LIKE :publisherName%")
    List<Publisher> findByPublisherName(@Param("publisherName") String publisherName);

    @Query("SELECT p FROM Publisher p WHERE p.publisherId = :publisherId AND p.publisherName LIKE :publisherName%")
    List<Publisher> findByPublisherIdAndPublisherName(@Param("publisherId") Long publisherId, @Param("publisherName") String publisherName);
}
