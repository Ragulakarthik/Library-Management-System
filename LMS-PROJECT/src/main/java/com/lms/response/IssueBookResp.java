package com.lms.response;

import lombok.Data;

@Data
public class IssueBookResp {

	private String message;
	
	public IssueBookResp(String message) {
		this.message=message;
	}
}
