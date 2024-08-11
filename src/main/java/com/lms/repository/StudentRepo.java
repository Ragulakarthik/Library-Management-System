package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
	public Student findByEmail(String email);

	@Query("SELECT S FROM Student S WHERE S.firstName LIKE :firstName%")
	public List<Student> getStudentByName(String firstName);

	@Query("SELECT S FROM Student S WHERE S.rollNo=:rollNo AND S.firstName LIKE :firstName%")
	public List<Student> getStudentByRollName(Long rollNo, String firstName);
	
}
