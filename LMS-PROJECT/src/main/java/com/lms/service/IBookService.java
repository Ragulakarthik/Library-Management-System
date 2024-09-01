package com.lms.service;

import java.util.List;

import com.lms.response.GetAllBooksResp;

public interface IBookService {
	public boolean addBook(Long publisherId, Long authorId, String bookTitle, Long quantity);

	public List<GetAllBooksResp> getBooksByParam(Long bookId, Long authorId, Long publisherId, String bookTitle);

	public boolean updateBook(Long bookId, Long publisherId, Long authorId, String bookTitle, Long quantity);

	public boolean deleteBook(Long bookId);
}
