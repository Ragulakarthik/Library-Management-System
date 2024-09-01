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
		IssueBookResp response;
		if(result) {
			response=new IssueBookResp("Book Issued Successfully");
			return ResponseEntity.ok(response);
		}
		else {
			response=new IssueBookResp("Book cannot be Issued");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<List<GetAllIssuedBooks>> getAllIssuedBooks(Long rollNo,Long bookId,String returned) {
		List<GetAllIssuedBooks> li=issueBookService.getAllIssuedBooks(rollNo,bookId,returned);
		return ResponseEntity.ok(li);
	}

	@Override
	public ResponseEntity<IssueBookResp> deleteIssueBook(Long issueBookId) {
		boolean result=issueBookService.deleteIssueBook(issueBookId);
		IssueBookResp response;
		if(result) {
			response=new IssueBookResp("IssueBook successfully deleted");
			return ResponseEntity.ok(response);
		}
		else {
			response=new IssueBookResp("IssueBookId not found with the given Id: "+issueBookId);
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<IssueBookResp> updateIssueBook(Long issueId,Long rollNo,Long bookId) {
		boolean result=issueBookService.updateIssueBook(issueId,rollNo,bookId);
		IssueBookResp response;
		if(result) {
			response=new IssueBookResp("IssuedBook updated Successfully");
			return ResponseEntity.ok(response);
		}
		else {
			response=new IssueBookResp("IssuedBook cannot be updated");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<IssueBookResp> returnBook(Long rollNo,Long bookId) {
		boolean result=issueBookService.returnBook(rollNo,bookId);
		IssueBookResp response;
		if(result) {
			response=new IssueBookResp("Book returned Successfully");
			return ResponseEntity.ok(response);
		}
		else {
			response=new IssueBookResp("Book cannot be returned");
			return ResponseEntity.badRequest().body(response);
		}
	}

}
