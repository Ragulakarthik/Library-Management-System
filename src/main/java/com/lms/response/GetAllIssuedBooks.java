package com.lms.response;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class GetAllIssuedBooks {
	
	private Long issueId;
	
	private Long rollNo;
	
	private Long bookId;
	
	private Date issuedDate;
	
	private Date endDate;
	
	private BigDecimal fine;
	
	public GetAllIssuedBooks() {
		
	}
	
	public GetAllIssuedBooks(long issueId,long rollNo,long bookId,Date issuedDate,Date endDate, BigDecimal fine) {
		this.issueId=issueId;
		this.rollNo=rollNo;
		this.bookId=bookId;
		this.issuedDate=issuedDate;
		this.endDate=endDate;
		this.fine=fine;
	}
}
