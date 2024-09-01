package com.lms.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.entity.Student;
import com.lms.request.EmailRequest;
import com.lms.request.StudentRequest;
import com.lms.request.UpdateStudentRequest;
import com.lms.response.GetAllStudentsResp;
import com.lms.response.StudentRegResp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public interface StudentApi {

	@Operation(summary = "This api is used to register the student") 
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Successfully registred.", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Student.class))}), 
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "Student not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid Student",content = @Content)}) 
	@PostMapping("/student/registerStudent")
    public ResponseEntity<StudentRegResp> registerStudent (@Valid @RequestBody StudentRequest studentRequest);
    
    @Operation(summary = "This api is used to verfiy the student by an OTP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent successfully.", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content) })
    @PostMapping("/student/OTPVerify")
    public ResponseEntity<StudentRegResp> OTPVerify(@Valid @RequestBody EmailRequest emailRequest);
    
    @Operation(summary = "This api is used to get the students by rollNo, firstName") 
   	@ApiResponses(value = { 
   			@ApiResponse(responseCode = "200", description = "List fetched sucessfully.", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Student.class))}), 
   			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
   	        @ApiResponse(responseCode = "404",description = "Student not found",content = @Content), 
   	        @ApiResponse(responseCode = "400",description = "Invalid Student",content = @Content)}) 
   	@GetMapping("/student/getStudentsByParam{rollNo}{firstName}")
       public ResponseEntity<List<GetAllStudentsResp>> getStudentsByParam (
    		   @Valid @RequestParam(required=false) Long rollNo,
    		   @Valid @RequestParam(required=false) @Pattern(regexp="^([A-Za-z]{1,25}( [A-Za-z]{1,25}){0,24})?$", message="studentName should contain only alphabet and size should not exceed 25 characters.") String studentName);
    
    @Operation(summary = "This api is used to update the student") 
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Successfully updated.", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Student.class))}), 
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "Student not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid Student",content = @Content)}) 
	@PutMapping("/student/updateStudent")
    public ResponseEntity<StudentRegResp> updateStudent (@Valid @RequestBody UpdateStudentRequest updateStudentRequest);
    
    @Operation(summary = "This api is used to delete the student") 
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Successfully deleted", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Student.class))}), 
			@ApiResponse(responseCode = "401",description = "Unauthorized user",content = @Content), 
	        @ApiResponse(responseCode = "404",description = "Student not found",content = @Content), 
	        @ApiResponse(responseCode = "400",description = "Invalid Student",content = @Content)}) 
	@DeleteMapping("/student/deleteStudent")
    public ResponseEntity<StudentRegResp> deleteStudent (@Valid @RequestParam @Positive(message="studentId should be greater 0") Long studentId);
}