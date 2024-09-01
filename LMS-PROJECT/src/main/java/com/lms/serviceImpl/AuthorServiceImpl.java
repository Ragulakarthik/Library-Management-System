package com.lms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lms.common.exception.ExtractMessage;
import com.lms.common.exception.LmsException;
import com.lms.entity.Author;
import com.lms.repository.AuthorRepo;
import com.lms.response.GetAllAuthorsResp;
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
	@Override
	public boolean updateAuthor(Long authorId, String authorName, String phoneNumber){
		try {
			Optional<Author> author=authorRepo.findById(authorId);
			if(author.isEmpty()) {
				throw new LmsException("Author with given id:"+authorId+" not found");
			}
			Author aut=author.get();
			aut.updateAuthor(authorName, phoneNumber);
			authorRepo.save(aut);
			return true;
		}
		catch (DataIntegrityViolationException e) {
			String errorMessage=String.format(e.getMessage());
			String errorResponse = extractMessage.extractErrorMessage(errorMessage);
			throw new LmsException(errorResponse);
		}
		catch (Exception e) {
			throw new LmsException(e.getMessage());
		}
	}
	@Override
	public List<GetAllAuthorsResp> getAuthorsByParam(Long authorId, String authorName) {
		List<Author> author;
		if(authorId==null && authorName==null) {
			author=authorRepo.findAll();
			if(author.isEmpty()) {
				throw new LmsException("No data found");
			}
		}
		else if(authorId==null && authorName!=null){
			author=authorRepo.findByAuthorName(authorName);
			if(author.isEmpty()) {
				throw new LmsException("No data found with given authorName");
			}
		}
		else if(authorId!=null && authorName==null) {
			Optional<Author> authors=authorRepo.findById(authorId);
			if(authors.isEmpty()) {
				throw new LmsException("No data found with the given authorId");
			}
			author = authors.stream().collect(Collectors.toList());
		}
		else {
			author=authorRepo.findByAuthorIdAuthorName(authorId, authorName);
			if(author.isEmpty()) {
				throw new LmsException("No data found wih the given author Id and author Name");
			}
		}
		List<GetAllAuthorsResp> getAllAuthorsResp=new ArrayList<>();
		for(Author au: author) {
			getAllAuthorsResp.add(new GetAllAuthorsResp(au));
		}
		return getAllAuthorsResp;
	}
	@Override
	public boolean deleteAuthor(Long authorId) {
		try {
			Optional<Author> author=authorRepo.findById(authorId);
			if(author.isEmpty()) {
				return false;
			}
			authorRepo.deleteById(authorId);
			return true;
		}catch (Exception e) {
			throw new LmsException(e.getMessage());
		}
	}

}
