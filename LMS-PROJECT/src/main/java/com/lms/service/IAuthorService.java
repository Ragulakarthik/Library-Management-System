package com.lms.service;

import java.util.List;

import com.lms.response.GetAllAuthorsResp;

public interface IAuthorService {
	public boolean registerAuthor(String authorName, String phoneNumber);
	
	public boolean updateAuthor(Long authorId,String authorName, String phoneNumber);
	
	public List<GetAllAuthorsResp> getAuthorsByParam(Long rollNo,String firstName);

	public boolean deleteAuthor(Long authorId);
}
