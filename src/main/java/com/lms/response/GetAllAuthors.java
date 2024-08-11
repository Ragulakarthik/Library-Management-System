package com.lms.response;

import lombok.Data;

@Data
public class GetAllAuthors {
	private long authorId;
	
	private String authorName;
	
	private String phoneNumber;
	
	public GetAllAuthors(long authorId, String authorName, String phoneNumber) {
		this.authorId=authorId;
		this.authorName=authorName;
		this.phoneNumber=phoneNumber;
	}
}