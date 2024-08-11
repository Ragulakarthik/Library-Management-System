package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.IssueBookApi;
import com.lms.response.GetAllIssuedBooks;
import com.lms.response.IssueBookResp;
import com.lms.service.IIssueBookService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class IssueBookController implements IssueBookApi{

	@Autowired
	private IIssueBookService issueBookService;
	
	@Override
	public ResponseEntity<IssueBookResp> issueBookToStudent(Long rollNo, Long bookId) {
		boolean result=issueBookService.issueBookToStudent(rollNo,bookId);
		IssueBookResp response=null;
		if(result) {
			response=new IssueBookResp("Book Issued Successfully, Return it within 10 other wise are needed to pay fine");
			return ResponseEntity.ok(response);
		}
		response=new IssueBookResp("Book cannot be Issued");
		return ResponseEntity.badRequest().body(response);
		
	}

	@Override
	public ResponseEntity<List<GetAllIssuedBooks>> getAllIssuedDetails(Long rollNo,Long bookId) {
		List<GetAllIssuedBooks> li=issueBookService.getAllIssuedBooks(rollNo,bookId);
		return ResponseEntity.ok(li);
	}

}
