package com.lms.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.common.exception.LmsException;
import com.lms.entity.Book;
import com.lms.entity.IssueBook;
import com.lms.entity.Student;
import com.lms.repository.BookRepo;
import com.lms.repository.IssueBookRepo;
import com.lms.repository.StudentRepo;
import com.lms.response.GetAllIssuedBooks;
import com.lms.service.IIssueBookService;

@Service
public class IssueBookServiceImpl implements IIssueBookService {

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private IssueBookRepo issueBookRepo;
	
	@Override
	public boolean issueBookToStudent(Long rollNo, Long bookId) {
		try {
			Optional<Student> student=studentRepo.findById(rollNo);
			if(student.isEmpty()) {
				throw new LmsException("Student with the rollNo :"+rollNo+" is not found");
			}
			Optional<Book> book= bookRepo.findById(bookId);
			if(book.isEmpty()) {
				throw new LmsException("Book with the given Id: "+bookId+" is not found");
			}
			Date issueDate = new Date();
	        // Calculate end date by adding 10 days to the issue date
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(issueDate);
	        calendar.add(Calendar.DAY_OF_MONTH, 10);
	        Date endDate = calendar.getTime();
	        
			IssueBook issue=new IssueBook();
			issue.setRollNo(rollNo);
			issue.setBookId(bookId);
			issue.setFine(BigDecimal.ZERO);
			issue.setIssueDate(issueDate);
			issue.setEndDate(endDate);
			issueBookRepo.save(issue);
			return true;
		}
		catch(Exception e) {
			throw new LmsException(e.getMessage());
		}
	}

	@Override
	public List<GetAllIssuedBooks> getAllIssuedBooks(Long rollNo,Long bookId) {
		if(rollNo==null && bookId==null) {
			List<IssueBook> result=issueBookRepo.findAll();
			if(result.isEmpty()) {
				throw new LmsException("No books were given to the students");
			}
			List<GetAllIssuedBooks> books=new ArrayList<>();
			for (IssueBook issueBook : result) {
		        GetAllIssuedBooks dto = new GetAllIssuedBooks();
		        
		        // Assuming GetAllIssuedBooks has corresponding setters
		        dto.setIssueId(issueBook.getIssueID());
		        dto.setRollNo(issueBook.getRollNo());
		        dto.setBookId(issueBook.getBookId());
		        dto.setFine(issueBook.getFine());
		        dto.setIssuedDate(issueBook.getIssueDate());
		        dto.setEndDate(issueBook.getEndDate());
		        
		        books.add(dto);
		    }
			return books;
		}
		else if(rollNo!=null && bookId==null){
			List<IssueBook> result=issueBookRepo.findByRollNo(rollNo);
			if(result.isEmpty()) {
				throw new LmsException("No books were issued to this student");
			}
			List<GetAllIssuedBooks> ans=new ArrayList<>();
			for(IssueBook issue : result) {
				GetAllIssuedBooks dto=new GetAllIssuedBooks();
				dto.setIssueId(issue.getIssueID());
				dto.setBookId(issue.getBookId());
				dto.setRollNo(issue.getRollNo());
				dto.setIssuedDate(issue.getIssueDate());
				dto.setEndDate(issue.getEndDate());
				dto.setFine(issue.getFine());
				ans.add(dto);
			}
			return ans;
		}
		else if(rollNo==null && bookId!=null) {
			List<IssueBook> result=issueBookRepo.findByBookId(bookId);
			if(result.isEmpty()) {
				throw new LmsException("No books were issued of this bookId");
			}
			List<GetAllIssuedBooks> ans=new ArrayList<>();
			for(IssueBook issue : result) {
				GetAllIssuedBooks dto=new GetAllIssuedBooks();
				dto.setIssueId(issue.getIssueID());
				dto.setBookId(issue.getBookId());
				dto.setRollNo(issue.getRollNo());
				dto.setIssuedDate(issue.getIssueDate());
				dto.setEndDate(issue.getEndDate());
				dto.setFine(issue.getFine());
				ans.add(dto);
			}
			return ans;
		}
		else {
			List<IssueBook> result=issueBookRepo.findByRollNoBookId(rollNo,bookId);
			if(result.isEmpty()) {
				throw new LmsException("No books were issued of this bookId");
			}
			List<GetAllIssuedBooks> ans=new ArrayList<>();
			for(IssueBook issue : result) {
				GetAllIssuedBooks dto=new GetAllIssuedBooks();
				dto.setIssueId(issue.getIssueID());
				dto.setBookId(issue.getBookId());
				dto.setRollNo(issue.getRollNo());
				dto.setIssuedDate(issue.getIssueDate());
				dto.setEndDate(issue.getEndDate());
				dto.setFine(issue.getFine());
				ans.add(dto);
			}
			return ans;
		}
	}

}
