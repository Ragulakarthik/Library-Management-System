package com.lms.service;

import java.math.BigDecimal;
import java.util.List;

import com.lms.response.GetAllBooksResp;

public interface IBookService {
	public boolean addBook(Long publisherId, Long authorId, String bookTitle, BigDecimal price);

	public List<GetAllBooksResp> getBooksByParam(Long bookId, Long authorId, Long publisherId, String bookTitle);
}
