package com.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.entity.IssueBook;

@Repository
public interface IssueBookRepo extends JpaRepository<IssueBook, Long> {

    List<IssueBook> findByRollNo(Long rollNo);

    List<IssueBook> findByBookId(Long bookId);

    @Query("SELECT ib FROM IssueBook ib WHERE ib.rollNo = :rollNo AND ib.bookId = :bookId")
    List<IssueBook> findByRollNoBookId(Long rollNo, Long bookId);

    @Query("SELECT ib FROM IssueBook ib WHERE ib.returned = :returned")
    List<IssueBook> findByReturned(String returned);

    @Query("SELECT ib FROM IssueBook ib WHERE ib.rollNo = :rollNo AND ib.returned = :returned")
    List<IssueBook> findByRollNoAndReturned(Long rollNo, String returned);

    @Query("SELECT ib FROM IssueBook ib WHERE ib.bookId = :bookId AND ib.returned = :returned")
    List<IssueBook> findByBookIdAndReturned(Long bookId, String returned);

    @Query("SELECT ib FROM IssueBook ib WHERE ib.rollNo = :rollNo AND ib.bookId = :bookId AND ib.returned = :returned")
    List<IssueBook> findByRollNoBookIdAndReturned(Long rollNo, Long bookId, String returned);
    
    @Query("SELECT ib FROM IssueBook ib WHERE ib.rollNo = :rollNo AND ib.bookId = :bookId")
	Optional<IssueBook> findByRollNoAndBookId(Long rollNo, Long bookId);
}
