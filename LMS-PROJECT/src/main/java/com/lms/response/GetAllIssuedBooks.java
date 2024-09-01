package com.lms.response;

import lombok.Data;

@Data
public class GetAllIssuedBooks {
	
	private Long issueId;
	
	private Long rollNo;
	
	private Long bookId;
	
	private String issuedDate;
	
	private String endDate;
	
	private Long fine;
	
	private String returned;
	
	public GetAllIssuedBooks() {
	}
	
	public GetAllIssuedBooks(long issueId,long rollNo,long bookId,String issuedDate,String endDate, Long fine, String returned) {
		this.issueId=issueId;
		this.rollNo=rollNo;
		this.bookId=bookId;
		this.issuedDate=issuedDate;
		this.endDate=endDate;
		this.fine=fine;
		this.returned=returned;
	}
}
