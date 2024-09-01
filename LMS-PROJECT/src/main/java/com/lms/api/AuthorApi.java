package com.lms.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.entity.Author;
import com.lms.response.AuthorRegResp;
import com.lms.response.GetAllAuthorsResp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public interface AuthorApi {
		@Operation(summary="This api is used to add new author")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200",description ="succefully added", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Author.class))}),
				@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
		        @ApiResponse(responseCode = "404",description = "author not found",content = @Content), 
		        @ApiResponse(responseCode = "400",description = "Invalid author",content = @Content)
		})
		@PostMapping("/author/registerAuthor")
		public ResponseEntity<AuthorRegResp> registerauthor(@Valid @RequestParam @Pattern(regexp="^([A-Za-z0-9]{1,25}( [A-Za-z0-9]{1,25}){0,24})?$", message="authorName should be alphanumberic and size should not exceed 25 characters.") String authorName,
				@Valid @RequestParam @Pattern(regexp="^[6-9]\\d{9}$", message="Phone Number can only start with 6,7,8,9 and it should contain 10 digits") String phoneNumber);
		
		@Operation(summary="This api is used to get authors by authorId, authorName")
		@ApiResponses(value= { @ApiResponse(responseCode="200",description="Sucessfully fetched",content= {@Content(mediaType = "application/json",schema=@Schema(implementation= Author.class))}),
				@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
		        @ApiResponse(responseCode = "404",description = "author not found",content = @Content), 
		        @ApiResponse(responseCode = "400",description = "Invalid author",content = @Content)
		})
		@GetMapping("/author/getAuthorsByParam{authorId}{authorName}")
		public ResponseEntity<List<GetAllAuthorsResp>> getAuthorsByParam(@Valid @RequestParam(required=false) Long authorId,
				@Valid @RequestParam(required=false) @Pattern(regexp="^([A-Za-z0-9]{1,25}( [A-Za-z0-9]{1,25}){0,24})?$", message="authorName should be alphanumberic and size should not exceed 25 characters.") String authorName);
		
		@Operation(summary="This api is used to udpate author")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200",description ="succefully updated", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Author.class))}),
				@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
		        @ApiResponse(responseCode = "404",description = "author not found",content = @Content), 
		        @ApiResponse(responseCode = "400",description = "Invalid author",content = @Content)
		})
		@PutMapping("/author/updateAuthor")
		public ResponseEntity<AuthorRegResp> udpateAuthor(@Valid @RequestParam @NotNull(message="authorId cannot be empty") Long authorId,
				@Valid @RequestParam @NotEmpty(message="authorName cannot be empty") @Pattern(regexp="^([A-Za-z0-9]{1,25}( [A-Za-z0-9]{1,25}){0,24})?$", message="authorName should be alphanumberic and size should not exceed 25 characters.") String authorName,
				@Valid @RequestParam @NotEmpty(message="phoneNumber cannot be empty") @Pattern(regexp="^[6-9]\\d{9}$", message="Phone Number can only start with 6,7,8,9 and it should contain 10 digits") String phoneNumber);
		
		@Operation(summary="This api is used to delete author")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200",description ="succefully deleted", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Author.class))}),
				@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
		        @ApiResponse(responseCode = "404",description = "author not found",content = @Content), 
		        @ApiResponse(responseCode = "400",description = "Invalid author",content = @Content)
		})
		@DeleteMapping("/author/deleteAuthor")
		public ResponseEntity<AuthorRegResp> deleteAuthor(@Valid @RequestParam @NotNull(message="authorId cannot be empty") Long authorId);
}
