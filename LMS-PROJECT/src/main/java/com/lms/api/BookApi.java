package com.lms.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.entity.Book;
import com.lms.response.BookRegResp;
import com.lms.response.GetAllBooksResp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
public interface BookApi {
	
	@Operation(summary="This api is used to add books")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description ="succefully added", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Book.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "Book not added",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid Book details provided",content = @Content)
	})
	@PostMapping("/book/addBook")
	public ResponseEntity<BookRegResp> addBook(
			@Valid @RequestParam @NotNull(message="publisherId cannot be a null value") @Min(value=1L, message="publisherId must be greater than 0") Long publisherId,
			@Valid @RequestParam @NotNull(message="authorId cannot be a null value")  @Min(value=1L, message="authorId must be greater that 0") Long authorId,
			@Valid @RequestParam @NotEmpty(message="bookTitle cannot be null or empty") @Pattern(regexp="^[a-zA-Z0-9 ',-:]{1,100}$",message="Book title must start with an alphanumeric character and may contain spaces, apostrophes, commas, hyphens, or colons between alphanumeric characters.") String bookTitle,
			@Valid @RequestParam @NotNull(message="quantity cannot be null") @Min(value=1L, message="quantity must be greater than 0") @Max(value=100L, message="quantity must be less than 100") Long quantity);
	
	@Operation(summary="This api is used to get books by the parameters")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description="Successfully fetched",content= {@Content(mediaType = "application/json",schema =@Schema(implementation =Book.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "Book not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid Book details provided",content = @Content)
	})
	@GetMapping("/book/getBooksByParam")
	public ResponseEntity<List<GetAllBooksResp>> getBooksByParam(
			@Valid @RequestParam(required=false) @Min(value=1, message="bookId cannot have less than or equal to 0") @Positive(message="bookId should be positive") Long bookId,
			@Valid @RequestParam(required=false) @Min(value=1, message="bookId cannot have less than or equal to 0") @Positive(message="bookId should be positive") Long authorId,
			@Valid @RequestParam(required=false) @Min(value=1, message="bookId cannot have less than or equal to 0") @Positive(message="bookId should be positive") Long publisherId,
			@Valid @RequestParam(required=false) @Pattern(regexp="^[a-zA-Z0-9 ',-:]{1,100}$",message="Book title must start with an alphanumeric character and may contain spaces, apostrophes, commas, hyphens, or colons between alphanumeric characters.")String bookTitle);
	
	@Operation(summary="This api is used to update book")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description ="succefully updated", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Book.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "Book not updated",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid Book details provided",content = @Content)
	})
	@PutMapping("/book/updateBook")
	public ResponseEntity<BookRegResp> updateBook(@Valid @RequestParam @NotNull(message="Book id cannot be empty") Long bookId,
			@Valid @RequestParam @NotNull(message="publisherId cannot be a null value") @Min(value=1L, message="publisherId must be greater that 0") Long publisherId,
			@Valid @RequestParam @NotNull(message="authorId cannot be a null value")  @Min(value=1L, message="authorId must be greater that 0") Long authorId,
			@Valid @RequestParam @NotEmpty(message="bookTitle cannot be null or empty") @Pattern(regexp="^[a-zA-Z0-9 ',-:]{1,100}$",message="Book title must start with an alphanumeric character and may contain spaces, apostrophes, commas, hyphens, or colons between alphanumeric characters.") String bookTitle,
			@Valid @RequestParam @NotNull(message="quantity cannot be null") @Min(value=1L, message="quantity must be greater than 0") @Max(value=100L, message="quantity must be less than 100") Long quantity);
	
	@Operation(summary="This api is used to delete book")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description ="succefully deleted", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Book.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "Book not updated",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid Book details provided",content = @Content)
	})
	@DeleteMapping("/book/deleteBook")
	public ResponseEntity<BookRegResp> deleteBook(@Valid @RequestParam @NotNull(message="Book id cannot be empty") Long bookId);
}
