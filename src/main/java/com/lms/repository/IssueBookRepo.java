package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.entity.IssueBook;

@Repository
public interface IssueBookRepo extends JpaRepository<IssueBook, Long>{

	List<IssueBook> findByRollNo(Long rollNo);

	List<IssueBook> findByBookId(Long bookId);

	@Query("SELECT ib FROM IssueBook ib WHERE ib.rollNo = :rollNo AND ib.bookId = :bookId")
	List<IssueBook> findByRollNoBookId(Long rollNo, Long bookId);
}
