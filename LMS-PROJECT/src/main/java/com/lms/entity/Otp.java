package com.lms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "otp")
public class Otp {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming ID is auto-generated
    private Long otpId;

	@NotEmpty
	@Column(name="email")
    private String email;
	
	@NotEmpty
	@Column(name="otp")
    private String otp;
	
	@NotNull
	@Column(name="expireTime")
    private Long expirationTime;
	
	@NotNull
	@Column(name="createdTime")
    private LocalDateTime createdTime;

	public Otp(String email,String otp,Long expirationTime) {
		this.email=email;
		this.otp=otp;
		this.expirationTime=expirationTime;
		this.createdTime=LocalDateTime.now();
	}
	public Otp() {
		
	}
}