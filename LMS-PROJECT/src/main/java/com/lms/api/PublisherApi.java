package com.lms.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.entity.Publisher;
import com.lms.response.GetAllPublishersResp;
import com.lms.response.PublisherRegResp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public interface PublisherApi {

	@Operation(summary="This api is used to add new publisher")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description ="succefully added", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Publisher.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "publisher not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid publisher",content = @Content)
	})
	@PostMapping("/publisher/registerPublisher")
	public ResponseEntity<PublisherRegResp> registerPublisher(@Valid @RequestParam @Pattern(regexp="^([A-Za-z0-9]{1,25}( [A-Za-z0-9]{1,25}){0,24})?$", message="PublisherName should contain only alphabet and size should not exceed 25 characters.") String publisherName,
			@Valid @RequestParam @Pattern(regexp="^[6-9]\\d{9}$", message="Phone Number can only start with 6,7,8,9 and it should contain 10 digits") String phoneNumber);
	
	@Operation(summary="This api is used to get publishers by param")
	@ApiResponses(value= { @ApiResponse(responseCode="200",description="Sucessfully fetched",content= {@Content(mediaType = "application/json",schema=@Schema(implementation= Publisher.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "publisher not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid publisher",content = @Content)
	})
	@GetMapping("/publisher/getPublishersByParam{publisherId}{publisherName}")
	public ResponseEntity<List<GetAllPublishersResp>> getPublishersByParam(@Valid @RequestParam(required=false) Long publisherId, 
			@Valid @RequestParam(required=false) @Pattern(regexp="^([A-Za-z0-9]{1,25}( [A-Za-z0-9]{1,25}){0,24})?$",message="PublisherName should contain only alphabet and size should not exceed 25 characters.") String publisherName);
	
	@Operation(summary="This api is used to update publisher")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description ="succefully added", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Publisher.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "publisher not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid publisher",content = @Content)
	})
	@PutMapping("/publisher/updatePublisher")
	public ResponseEntity<PublisherRegResp> updatePublisher(@Valid @RequestParam @NotNull(message="publisherId cannot be empty") Long publisherId,
			@Valid @RequestParam @Pattern(regexp="^([A-Za-z0-9]{1,25}( [A-Za-z0-9]{1,25}){0,24})?$", message="PublisherName should contain only alphabet and size should not exceed 25 characters.") String publisherName,
			@Valid @RequestParam @Pattern(regexp="^[6-9]\\d{9}$", message="Phone Number can only start with 6,7,8,9 and it should contain 10 digits") String phoneNumber);
	
	@Operation(summary="This api is used to delete publisher")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description ="succefully deleted", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Publisher.class))}),
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "publisher not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid publisher",content = @Content)
	})
	@DeleteMapping("/publisher/deletePublisher")
	public ResponseEntity<PublisherRegResp> deletePublisher(@Valid @RequestParam @NotNull(message="publisherId cannot be empty") Long publisherId);
	
}
