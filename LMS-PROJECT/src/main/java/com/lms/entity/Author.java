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
@Table(name = "t_author")
@Data
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="authorId",nullable=false,unique=true)
	private Long authorId;
	
	@Column(name="authorName", nullable=false)
	@NotEmpty
	private String authorName;
	
	@Column(name="phoneNumber",nullable=false, unique=true)
	@NotEmpty
	private String phoneNumber;
	
	public void updateAuthor(String authorName, String phoneNumber) {
		this.authorName=authorName;
		this.phoneNumber=phoneNumber;
	}
}
