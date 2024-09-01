package com.lms.response;

import lombok.Data;

@Data
public class BookRegResp {
	
	private String message;
	
	public BookRegResp(String message) {
		this.message=message;
	}
}
