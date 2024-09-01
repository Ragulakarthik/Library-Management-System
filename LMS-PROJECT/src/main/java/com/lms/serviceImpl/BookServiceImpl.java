package com.lms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lms.common.exception.ExtractMessage;
import com.lms.common.exception.LmsException;
import com.lms.entity.Author;
import com.lms.entity.Book;
import com.lms.entity.Publisher;
import com.lms.repository.AuthorRepo;
import com.lms.repository.BookRepo;
import com.lms.repository.PublisherRepo;
import com.lms.response.GetAllBooksResp;
import com.lms.service.IBookService;

@Service
public class BookServiceImpl implements IBookService{

	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private AuthorRepo authorRepo;
	
	@Autowired
	private PublisherRepo publisherRepo;
	
	@Autowired
	private ExtractMessage extractMessage;
	
	@Override
	public boolean addBook(Long publisherId, Long authorId, String bookTitle, Long quantity) {
		try {
		Optional<Publisher> publisher=publisherRepo.findById(publisherId);
		if(!publisher.isPresent()) {
			throw new LmsException("Publisher with the given Id is not found");
		}
		Optional<Author> author=authorRepo.findById(authorId);
		if(!author.isPresent()) {
			throw new LmsException("Author with the given Id is not found");
		}
		Book book=new Book();
		book.setBookTitle(bookTitle);
		book.setQuantity(quantity);
		book.setAuthorId(authorId);
		book.setPublisherId(publisherId);
		bookRepo.save(book);		
		return true;
		}
		catch(NumberFormatException e) {
			throw new LmsException(e.getMessage());
		}
		catch(TypeMismatchException e) {
			throw new LmsException(e.getMessage());
		}
		catch(DataIntegrityViolationException e) {
			String errorMessage=String.format(e.getMessage());
			String errorResponse = extractMessage.extractErrorMessage(errorMessage);
			throw new LmsException(errorResponse);
		}
	}

	@Override
	public List<GetAllBooksResp> getBooksByParam(Long bookId, Long authorId, Long publisherId, String bookTitle) {
		try {
			List<GetAllBooksResp> response =new ArrayList<>();
			List<Book> list=null;
			if (bookId == null && authorId == null && publisherId == null && bookTitle == null) {
		        list = bookRepo.findAll();
		    } else if (bookId != null && authorId == null && publisherId == null && bookTitle == null) {
		        list = bookRepo.findByBookId(bookId);
		    } else if (bookId == null && authorId != null && publisherId == null && bookTitle == null) {
		        list = bookRepo.findByAuthorId(authorId);
		    } else if (bookId == null && authorId == null && publisherId != null && bookTitle == null) {
		        list = bookRepo.findByPublisherId(publisherId);
		    } else if (bookId == null && authorId == null && publisherId == null && bookTitle != null) {
		        list = bookRepo.findByBookTitle(bookTitle);
		    } else if (bookId != null && authorId != null && publisherId == null && bookTitle == null) {
		        list = bookRepo.findByBookIdAndAuthorId(bookId, authorId);
		    } else if (bookId != null && authorId == null && publisherId != null && bookTitle == null) {
		        list = bookRepo.findByBookIdAndPublisherId(bookId, publisherId);
		    } else if (bookId != null && authorId == null && publisherId == null && bookTitle != null) {
		        list = bookRepo.findByBookIdAndBookTitle(bookId, bookTitle);
		    } else if (bookId == null && authorId != null && publisherId != null && bookTitle == null) {
		        list = bookRepo.findByAuthorIdAndPublisherId(authorId, publisherId);
		    } else if (bookId == null && authorId != null && publisherId == null && bookTitle != null) {
		        list = bookRepo.findByAuthorIdAndBookTitle(authorId, bookTitle);
		    } else if (bookId == null && authorId == null && publisherId != null && bookTitle != null) {
		        list = bookRepo.findByPublisherIdAndBookTitle(publisherId, bookTitle);
		    } else if (bookId != null && authorId != null && publisherId != null && bookTitle == null) {
		        list = bookRepo.findByBookIdAndAuthorIdAndPublisherId(bookId, authorId, publisherId);
		    } else if (bookId != null && authorId != null && publisherId == null && bookTitle != null) {
		        list = bookRepo.findByBookIdAndAuthorIdAndBookTitle(bookId, authorId, bookTitle);
		    } else if (bookId != null && authorId == null && publisherId != null && bookTitle != null) {
		        list = bookRepo.findByBookIdAndPublisherIdAndBookTitle(bookId, publisherId, bookTitle);
		    } else if (bookId == null && authorId != null && publisherId != null && bookTitle != null) {
		        list = bookRepo.findByAuthorIdAndPublisherIdAndBookTitle(authorId, publisherId, bookTitle);
		    } else if (bookId != null && authorId != null && publisherId != null && bookTitle != null) {
		        list = bookRepo.findByBookIdAndAuthorIdAndPublisherIdAndBookTitle(bookId, authorId, publisherId, bookTitle);
		    } 
	
			if (list.isEmpty()) {
		        throw new LmsException("No books found");
		    }
		    for (Book li : list) {
		        GetAllBooksResp ans = new GetAllBooksResp();
		        ans.setAuthorId(li.getAuthorId());
		        ans.setBookId(li.getBookId());
		        ans.setBookTitle(li.getBookTitle());
		        ans.setQuantity(li.getQuantity());
		        ans.setPublisherId(li.getPublisherId());
		        response.add(ans);
		    }
		    if (response.isEmpty()) {
		        throw new LmsException("No books found");
		    }
		    return response;
		}
		catch(Exception e) {
			throw new LmsException(e.getMessage());
		}
	}

	@Override
	public boolean updateBook(Long bookId,Long publisherId,Long authorId,String bookTitle,Long quantity) {
		try {
			Optional<Book> book=bookRepo.findById(bookId);
			if(book.isEmpty()) {
				throw new LmsException("No book found with the given Id:"+bookId);
			}
			Optional<Publisher> publisher=publisherRepo.findById(publisherId);
			if(publisher.isEmpty()) {
				throw new LmsException("No publisher found with the given Id:"+publisherId);
			}
			Optional<Author> author=authorRepo.findById(authorId);
			if(author.isEmpty()) {
				throw new LmsException("No author found with the given Id:"+authorId);
			}
			Book bk=book.get();
			bk.updateBook(publisherId,authorId,bookTitle,quantity);
			bookRepo.save(bk);
			return true;
		}
		catch (Exception e) {
			throw new LmsException(e.getMessage());
		}
	}

	@Override
	public boolean deleteBook(Long bookId) {
		try {
			Optional<Book> book=bookRepo.findById(bookId);
			if(book.isEmpty()) {
				return false;
			}
			bookRepo.deleteById(bookId);
			return true;
		}catch (Exception e) {
			throw new LmsException(e.getMessage());
		}
	}

}
