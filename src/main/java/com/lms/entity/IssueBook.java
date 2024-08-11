package com.lms.entity;

import java.math.BigDecimal;
import java.util.Date;

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
public class IssueBook {
	
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
	private Date issueDate;
	
	@Column(name="endDate")
	@NotNull
	private Date endDate;
	
	@Column(name="fine")
	@NotNull
	private BigDecimal fine;
}
