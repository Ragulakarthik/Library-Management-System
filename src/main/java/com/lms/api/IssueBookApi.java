package com.lms.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.entity.IssueBook;
import com.lms.response.GetAllIssuedBooks;
import com.lms.response.IssueBookResp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public interface IssueBookApi {

	@Operation(summary="This api is used to issue book to the student")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description ="succefully issued", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = IssueBook.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "Student not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid Student",content = @Content)
	})
	@PostMapping("/issuebook/issueBookToStudent")
	public ResponseEntity<IssueBookResp> issueBookToStudent(@Valid @Positive(message="rollNo must be greater than 0") @RequestParam @Min(value=1, message="rollNo cannot be less than or equal to 0")Long rollNo,@Valid @Positive(message="bookId must be greater than 0") @RequestParam @Min(value=1, message="bookId cannot be less than or equal to 0") Long bookId);
	
	@Operation(summary="This api is used to get all issued book details")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description ="succefully issued", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = IssueBook.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "Student not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid Student",content = @Content)
	})
	@GetMapping("/issuebook/getAllIssuedBooks")
	public ResponseEntity<List<GetAllIssuedBooks>> getAllIssuedDetails(@Valid @RequestParam(required=false) @Positive(message="rollNo must be greater than 0") @Min(value=1, message="rollNo cannot be less than or equal to 0") Long rollNo
			,@Valid @RequestParam(required=false) @Positive(message="bookId must be greater than 0") @Min(value=1, message="bookId cannot be less than or equal to 0") Long bookId);
}
