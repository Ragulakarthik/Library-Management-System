package com.lms.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
import com.lms.service.IOtpService;

@Service
public class IssueBookServiceImpl implements IIssueBookService {

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private IssueBookRepo issueBookRepo;
	
	@Autowired
	private IOtpService otpService;
	
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
			List<IssueBook> issueBook=issueBookRepo.findByRollNoBookId(rollNo, bookId);
			for(IssueBook issue:issueBook) {
				if(issue.getReturned().equals("0")) {
					throw new LmsException("The book "+book.get().getBookTitle()+" is already to issued to you on "+issue.getIssueDate()+". But you didn't return it yet.");
				}
			}
			Date issueDate = new Date();
	        // Calculate end date by adding 10 days to the issue date
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(issueDate);
	        calendar.add(Calendar.DAY_OF_MONTH, 10);
	        Date endDate = calendar.getTime();
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        String issueDateString = dateFormat.format(issueDate);
	        String endDateString = dateFormat.format(endDate);
	        
	        if(book.get().getQuantity()==0) {
	        	throw new LmsException("The book is not available, Come back tommorow");
	        }
	        
	        book.get().setQuantity(book.get().getQuantity()-1);
	        
	        String msg="Dear "+student.get().getFirstName()+", The Book "+book.get().getBookTitle()+" Issued Successfully on "+issueDateString+". Please return it within "+endDateString+". Failure to do so will result in a fine of 10rs/Day.";
	        String sub="BOOK ISSUED "+book.get().getBookTitle();
	        otpService.sendIssueBookMessage(student.get().getEmail(), msg,sub);
	        
			IssueBook issue=new IssueBook();
			issue.setRollNo(rollNo);
			issue.setBookId(bookId);
			issue.setFine(0L);
			issue.setIssueDate(issueDateString);
			issue.setEndDate(endDateString);
			issue.setReturned("0");
			issueBookRepo.save(issue);
			return true;
		}
		catch(Exception e) {
			throw new LmsException(e.getMessage());
		}
	}

	@Override
	public List<GetAllIssuedBooks> getAllIssuedBooks(Long rollNo, Long bookId, String returned) {
	    List<IssueBook> result;
	    if (rollNo == null && bookId == null) {
	        if (returned == null) {
	            result = issueBookRepo.findAll();
	            if (result.isEmpty()) {
	                throw new LmsException("No books were issued.");
	            }
	        } else {
	            result = issueBookRepo.findByReturned(returned);
	            if (result.isEmpty()) {
	                throw new LmsException("No books were returned.");
	            }
	        }
	    } else if (rollNo != null && bookId == null) {
	        if (returned == null) {
	            result = issueBookRepo.findByRollNo(rollNo);
	            if (result.isEmpty()) {
	                throw new LmsException("No books were issued to this student.");
	            }
	        } else {
	            result = issueBookRepo.findByRollNoAndReturned(rollNo, returned);
	            if (result.isEmpty()) {
	                throw new LmsException("No book were returned by this student");
	            }
	        }
	    } else if (rollNo == null && bookId != null) {
	        if (returned == null) {
	            result = issueBookRepo.findByBookId(bookId);
	            if (result.isEmpty()) {
	                throw new LmsException("No books were issued with this bookId.");
	            }
	        } else {
	            result = issueBookRepo.findByBookIdAndReturned(bookId, returned);
	            if (result.isEmpty()) {
	                throw new LmsException("No books were returned with this bookID");
	            }
	        }
	    } else {
	        if (returned == null) {
	            result = issueBookRepo.findByRollNoBookId(rollNo, bookId);
	            if (result.isEmpty()) {
	                throw new LmsException("No books were issued with the specified roll number and bookId.");
	            }
	        } else {
	            result = issueBookRepo.findByRollNoBookIdAndReturned(rollNo, bookId, returned);
	            if (result.isEmpty()) {
	                throw new LmsException("No books were returned with the specified roll number and bookId.");
	            }
	        }
	    }
	    List<GetAllIssuedBooks> books = new ArrayList<>();
	    for (IssueBook issueBook : result) {
	        GetAllIssuedBooks dto = new GetAllIssuedBooks();
	        dto.setIssueId(issueBook.getIssueID());
	        dto.setRollNo(issueBook.getRollNo());
	        dto.setBookId(issueBook.getBookId());
	        dto.setFine(issueBook.getFine());
	        dto.setIssuedDate(issueBook.getIssueDate());
	        dto.setEndDate(issueBook.getEndDate());
	        dto.setReturned(issueBook.getReturned());
	        books.add(dto);
	    }

	    return books;
	}


	@Override
	public boolean deleteIssueBook(Long issueBookId) {
		Optional<IssueBook> issueBook=issueBookRepo.findById(issueBookId);
		if(issueBook.isEmpty()) {
			return false;
		}
		issueBookRepo.deleteById(issueBookId);
		return true;
	}

	@Override
	public boolean updateIssueBook(Long issueId, Long rollNo, Long bookId) {
	    try {
	        Optional<IssueBook> existingIssue = issueBookRepo.findById(issueId);
	        if (existingIssue.isEmpty()) {
	            throw new LmsException("No record found with the given issueId");
	        }

	        Optional<Student> student = studentRepo.findById(rollNo);
	        if (student.isEmpty()) {
	            throw new LmsException("Student with the rollNo: " + rollNo + " is not found");
	        }

	        Optional<Book> newBook = bookRepo.findById(bookId);
	        if (newBook.isEmpty()) {
	            throw new LmsException("Book with the given Id: " + bookId + " is not found");
	        }

	        IssueBook issueRecord = existingIssue.get();
	        boolean isBookChanged = !issueRecord.getBookId().equals(bookId);
	        boolean isRollNoChanged = !issueRecord.getRollNo().equals(rollNo);

	        // If bookId is changed, adjust the quantities of the old and new books
	        if (isBookChanged) {
	            Optional<Book> originalBook = bookRepo.findById(issueRecord.getBookId());
	            if (originalBook.isPresent()) {
	                originalBook.get().setQuantity(originalBook.get().getQuantity() + 1); // Increase quantity of original book
	                bookRepo.save(originalBook.get());
	            }
	            if (newBook.get().getQuantity() == 0) {
	                throw new LmsException("The new book is not available, Come back tomorrow");
	            }
	            newBook.get().setQuantity(newBook.get().getQuantity() - 1); // Decrease quantity of new book
	        }

	        // Save the updated book quantity
	        bookRepo.save(newBook.get());

	        // If rollNo is changed, check for conflicts and update
	        if (isRollNoChanged) {
	            List<IssueBook> issueBookList = issueBookRepo.findByRollNoBookId(rollNo, bookId);
	            for (IssueBook issue : issueBookList) {
	                if (!issue.getReturned().equals("1") && !issue.getIssueID().equals(issueId)) {
	                    throw new LmsException("The book " + newBook.get().getBookTitle() + " is already issued to this roll number on " + issue.getIssueDate() + ". But it hasn't been returned yet.");
	                }
	            }
	        }

	        // Prepare and send notification message
	        Date issueDate = new Date();
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(issueDate);
	        calendar.add(Calendar.DAY_OF_MONTH, 10);
	        Date endDate = calendar.getTime();

	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        String issueDateString = dateFormat.format(issueDate);
	        String endDateString = dateFormat.format(endDate);

	        String msg = "Dear " + student.get().getFirstName() + ", The Book " + newBook.get().getBookTitle() + " has been successfully issued on " + issueDateString + ". Please return it by " + endDateString + " to avoid any fines.";
	        String sub = "UPDATE OF ISSUED BOOK";
	        otpService.sendIssueBookMessage(student.get().getEmail(), msg, sub);

	        // Update the issue record
	        issueRecord.updateIssueBook(rollNo, bookId, issueDateString, endDateString);
	        issueBookRepo.save(issueRecord);

	        return true;
	    } catch (Exception e) {
	        throw new LmsException(e.getMessage());
	    }
	}


	@Override
	public boolean returnBook(Long rollNo, Long bookId) {
		try {
			List<IssueBook> issueBook=issueBookRepo.findByRollNoBookId(rollNo,bookId);
			if(issueBook.isEmpty()){
				throw new LmsException("No records found with the given details");
			}
			IssueBook issueBook1=null;
			for(IssueBook issue:issueBook) {
				if(issue.getReturned().equals("0")) {
					 issueBook1=issue;
					 break;
				}
			}
			if(issueBook1==null) {
				throw new LmsException("This book is already returned");
			}
			
			String dueDateString = issueBook1.getEndDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        Date dueDate = dateFormat.parse(dueDateString);
	        Date currentDate = new Date();

	        // Calculate fine if overdue
	        if(issueBook1.getFine()==0) {
		        if (currentDate.after(dueDate)) {
		            long diffInMillies = Math.abs(currentDate.getTime() - dueDate.getTime());
		            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		            Long fine = diffInDays * 10; // Rs 10 per day
		            issueBook1.setFine(fine);
		            throw new LmsException("Pay the fine and proceed with the returing process. FINE : "+fine);
		        }
	        }
	        else {
	        	throw new LmsException("Pay the fine and proceed with the returing process. FINE : "+issueBook1.getFine());
	        }
	        
	        Optional<Book> originalBook = bookRepo.findById(issueBook1.getBookId());
	        if (originalBook.isPresent()) {
	            originalBook.get().setQuantity(originalBook.get().getQuantity() + 1); // Increase quantity of original book
	            bookRepo.save(originalBook.get());
	        }
	        Optional<Student> student = studentRepo.findById(rollNo);
	        if (student.isEmpty()) {
	            throw new LmsException("Student with the rollNo: " + rollNo + " is not found");
	        }
	        Optional<Book> newBook = bookRepo.findById(bookId);
	        if (newBook.isEmpty()) {
	            throw new LmsException("Book with the given Id: " + bookId + " is not found");
	        }
	        Date issueDate = new Date();
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(issueDate);
	        calendar.add(Calendar.DAY_OF_MONTH, 10);
	
	        String issueDateString = dateFormat.format(issueDate);
	
	        String msg = "Dear " + student.get().getFirstName() + ", The Book " + newBook.get().getBookTitle() + " returned Successfully on " + issueDateString;
	        String sub = "STATUS OF ISSUED BOOK-RETURNED SUCCESSFULLY";
	        otpService.sendIssueBookMessage(student.get().getEmail(), msg, sub);
	        issueBook1.setReturned("1");
	        issueBookRepo.save(issueBook1);
			return true;
		}
		catch(Exception e) {
			throw new LmsException(e.getMessage());
		}
	}
}
