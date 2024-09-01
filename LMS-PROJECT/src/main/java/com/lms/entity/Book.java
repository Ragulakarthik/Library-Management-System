package com.lms.entity;

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
@Table(name="t_book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bookId",nullable=false,unique=true)
	private Long bookId;
	
	@Column(name="publisherId",nullable=false)
	@NotNull
	private Long publisherId;
	
	@Column(name="authorId",nullable=false)
	@NotNull
	private Long authorId;
	
	@Column(name="bookTitle",nullable=false)
	@NotEmpty
	private String bookTitle;
	
	@Column(name="quantity",nullable=false)
	@NotNull
	private Long quantity;

	public void updateBook(Long publisherId2, Long authorId2, String bookTitle2, Long quantity2) {
		this.publisherId=publisherId2;
		this.authorId=authorId2;
		this.bookTitle=bookTitle2;
		this.quantity=quantity2;
	}
}
