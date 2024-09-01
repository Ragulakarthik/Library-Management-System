package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.BookApi;
import com.lms.response.BookRegResp;
import com.lms.response.GetAllBooksResp;
import com.lms.service.IBookService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class BookController implements BookApi {

	@Autowired
	private IBookService bookService;
	
	@Override
	public ResponseEntity<BookRegResp> addBook(Long publisherId, Long authorId, String bookTitle, Long quantity) {
		boolean result=bookService.addBook(publisherId,authorId,bookTitle,quantity);
		if(result) {
			String response="Book added successfully";
			BookRegResp ans=new BookRegResp(response);
			return ResponseEntity.ok(ans);
		}
		else {
			String response="Book adding failed";
			BookRegResp ans=new BookRegResp(response);
			return ResponseEntity.badRequest().body(ans);
		}
	}

	@Override
	public ResponseEntity<List<GetAllBooksResp>> getBooksByParam(Long bookId,Long authorId,Long publisherId,String bookTitle) {
		List<GetAllBooksResp> response=bookService.getBooksByParam(bookId,authorId,publisherId,bookTitle);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BookRegResp> updateBook(Long bookId,Long publisherId,Long authorId,String bookTitle,Long quantity) {
		boolean result=bookService.updateBook(bookId,publisherId,authorId,bookTitle,quantity);
		BookRegResp bookRegResp;
		if(result) {
			String response="Book Updated Successfully";
			bookRegResp=new BookRegResp(response);
			return ResponseEntity.ok(bookRegResp);
		}
		else {
			String response="Book update failed";
			bookRegResp=new BookRegResp(response);
			return ResponseEntity.badRequest().body(bookRegResp);
		}
		
	}

	@Override
	public ResponseEntity<BookRegResp> deleteBook(Long bookId) {
		boolean result=bookService.deleteBook(bookId);
		if(result) {
			String response="Book deleted successfully";
			BookRegResp ans=new BookRegResp(response);
			return ResponseEntity.ok(ans);
		}
		else {
			String response="Book not found with the given bookId: "+bookId;
			BookRegResp ans=new BookRegResp(response);
			return ResponseEntity.badRequest().body(ans);
		}
	}

}
