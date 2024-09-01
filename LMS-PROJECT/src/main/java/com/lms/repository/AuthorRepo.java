package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.entity.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.authorName LIKE :authorName%")
    public List<Author> findByAuthorName(@Param("authorName") String authorName);

    @Query("SELECT a FROM Author a WHERE a.authorId = :authorId AND a.authorName LIKE :authorName%")
    public List<Author> findByAuthorIdAuthorName(@Param("authorId") Long authorId, @Param("authorName") String authorName);
}
