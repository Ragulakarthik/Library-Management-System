package com.lms.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.BookApi;
import com.lms.common.exception.LmsException;
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
	public ResponseEntity<BookRegResp> addBook(Long publisherId, Long authorId, String bookTitle, BigDecimal price) {
		boolean result=bookService.addBook(publisherId,authorId,bookTitle,price);
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

}
