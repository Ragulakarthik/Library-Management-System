package com.lms.response;

import lombok.Data;

@Data
public class GetAllBooksResp {
	
	public Long bookId;
	
	public Long authorId;
	
	public Long publisherId;
	
	public String bookTitle;
	
	public Long quantity;
	
	public GetAllBooksResp() {
		
	}
	public GetAllBooksResp(long bookId,long authorId,long publisherId,String bookTitle,Long quantity) {
		this.bookId=bookId;
		this.authorId=authorId;
		this.publisherId=publisherId;
		this.bookTitle=bookTitle;
		this.quantity=quantity;
	}
}
