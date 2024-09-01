package com.lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="t_issueBook")
public class  IssueBook{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="issueId")
	private Long issueID;
	
	@Column(name="rollNo")
	@NotNull
	private Long rollNo;
	
	@Column(name="bookId")
	@NotNull
	private Long bookId;
	
	@Column(name="issueDate")
	@NotNull
	private String issueDate;
	
	@Column(name="endDate")
	@NotNull
	private String endDate;
	
	@Column(name="fine")
	@NotNull
	private Long fine;
	
	@Column(name="returned")
	@NotNull
	private String returned;

	public void updateIssueBook(Long rollNo,Long bookId,String issueDate,String endDate) {
		this.rollNo=rollNo;
		this.bookId=bookId;
		this.endDate=endDate;
		this.issueDate=issueDate;
	}
}
