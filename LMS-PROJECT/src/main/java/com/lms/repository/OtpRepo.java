package com.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.entity.Otp;

public interface OtpRepo extends JpaRepository<Otp, Long>{
	 @Query("SELECT o FROM Otp o WHERE o.email = :email ORDER BY o.createdTime DESC")
	 Optional<Otp> findLatestOtpByEmail(String email);

	void deleteAllByEmail(String email);
}
