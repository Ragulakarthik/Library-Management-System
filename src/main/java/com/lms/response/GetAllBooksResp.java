package com.lms.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class GetAllBooksResp {
	
	public Long bookId;
	
	public Long authorId;
	
	public Long publisherId;
	
	public String bookTitle;
	
	public BigDecimal price;
	
	public GetAllBooksResp() {
		
	}
	public GetAllBooksResp(long bookId,long authorId,long publisherId,String bookTitle,BigDecimal price) {
		this.bookId=bookId;
		this.authorId=authorId;
		this.publisherId=publisherId;
		this.bookTitle=bookTitle;
		this.price=price;
	}
}
