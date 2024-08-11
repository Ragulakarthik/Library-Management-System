package com.lms.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
	
	@Column(name="price",nullable=false)
	@Positive
	private BigDecimal price;
}
