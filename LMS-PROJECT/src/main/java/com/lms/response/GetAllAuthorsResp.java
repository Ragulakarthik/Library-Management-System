package com.lms.response;

import com.lms.entity.Author;

import lombok.Data;

@Data
public class GetAllAuthorsResp {
	private long authorId;
	
	private String authorName;
	
	private String phoneNumber;
	
	public GetAllAuthorsResp(long authorId, String authorName, String phoneNumber) {
		this.authorId=authorId;
		this.authorName=authorName;
		this.phoneNumber=phoneNumber;
	}
	public GetAllAuthorsResp(Author author) {
		this.authorId=author.getAuthorId();
		this.authorName=author.getAuthorName();
		this.phoneNumber=author.getPhoneNumber();
	}
}