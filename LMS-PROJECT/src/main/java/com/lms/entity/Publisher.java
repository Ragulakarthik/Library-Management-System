package com.lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "t_publisher")
@Data
public class Publisher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="publisherId",nullable=false,unique=true)
	private Long publisherId;
	
	@Column(name="publisherName", nullable=false)
	@NotEmpty
	private String publisherName;
	
	@Column(name="phoneNumber",nullable=false, unique=true)
	@NotEmpty
	private String phoneNumber;
	
	public void updatePublisher(String publisherName,String phoneNumber) {
		this.publisherName=publisherName;
		this.phoneNumber=phoneNumber;
	}
}
