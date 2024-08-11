package com.lms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lms.common.exception.ExtractMessage;
import com.lms.common.exception.LmsException;
import com.lms.entity.Author;
import com.lms.repository.AuthorRepo;
import com.lms.service.IAuthorService;

@Service
public class AuthorServiceImpl implements IAuthorService {

	@Autowired
	private AuthorRepo authorRepo;
	
	@Autowired
	private ExtractMessage extractMessage; 
	@Override
	public boolean registerAuthor(String authorName, String phoneNumber) {
		try {
			Author author=new Author();
			author.setAuthorName(authorName);
			author.setPhoneNumber(phoneNumber);
			authorRepo.save(author);
			return true;
		}
		catch (DataIntegrityViolationException e) {
			String errorMessage=String.format(e.getMessage());
			String errorResponse = extractMessage.extractErrorMessage(errorMessage);
			throw new LmsException(errorResponse);
		}
		catch (Exception e) {
			return false;
		}
	}

}
