package com.lms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.AuthorApi;
import com.lms.common.exception.LmsException;
import com.lms.entity.Author;
import com.lms.repository.AuthorRepo;
import com.lms.response.AuthorRegResp;
import com.lms.response.GetAllAuthors;
import com.lms.service.IAuthorService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AuthorController implements AuthorApi{

	@Autowired
	private IAuthorService authorService;
	
	@Autowired
	private AuthorRepo authorRepo;
	
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
	public ResponseEntity<List<GetAllAuthors>> getAllAuthors() {
		List<Author> list=authorRepo.findAll();
		List<GetAllAuthors> response =list.stream()
                .map(res -> new GetAllAuthors(
                		res.getAuthorId(),
                		res.getAuthorName(),
                		res.getPhoneNumber()))
                .collect(Collectors.toList());
		if(response.size()==0) {
			throw new LmsException("No data found");
		}
		return ResponseEntity.ok(response);
	}
	

}
