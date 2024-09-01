package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.AuthorApi;
import com.lms.response.AuthorRegResp;
import com.lms.response.GetAllAuthorsResp;
import com.lms.service.IAuthorService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AuthorController implements AuthorApi{

	@Autowired
	private IAuthorService authorService;
	
	@Override
	public ResponseEntity<AuthorRegResp> registerauthor( String authorName, String phoneNumber) {
		boolean result=authorService.registerAuthor(authorName,phoneNumber);
		if(result==true) {
			AuthorRegResp response=new AuthorRegResp("Author added successfully");
			return ResponseEntity.ok(response);
		}
		else {
			AuthorRegResp response=new AuthorRegResp("Failed to add author");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<List<GetAllAuthorsResp>> getAuthorsByParam(Long authorId, String authorName) {
		List<GetAllAuthorsResp> response =authorService.getAuthorsByParam(authorId,authorName );
		if(response.size()>0) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}

	@Override
	public ResponseEntity<AuthorRegResp> udpateAuthor(Long authorId,String authorName,String phoneNumber) {
		boolean result=authorService.updateAuthor(authorId,authorName,phoneNumber);
		if(result==true) {
			AuthorRegResp response=new AuthorRegResp("Author updated successfully");
			return ResponseEntity.ok(response);
		}
		else {
			AuthorRegResp response=new AuthorRegResp("Failed to update author");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<AuthorRegResp> deleteAuthor(Long authorId) {
		boolean result=authorService.deleteAuthor(authorId);
		if(result==true) {
			AuthorRegResp response=new AuthorRegResp("Author deleted successfully");
			return ResponseEntity.ok(response);
		}
		else {
			AuthorRegResp response=new AuthorRegResp("Author not found with the given Id: "+authorId);
			return ResponseEntity.badRequest().body(response);
		}
	}
}
