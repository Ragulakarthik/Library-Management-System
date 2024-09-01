package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.StudentApi;
import com.lms.request.EmailRequest;
import com.lms.request.StudentRequest;
import com.lms.request.UpdateStudentRequest;
import com.lms.response.GetAllStudentsResp;
import com.lms.response.StudentRegResp;
import com.lms.service.IStudentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class StudentController implements StudentApi {
	
	@Autowired
	private IStudentService studentService;

	 @Override
	 public ResponseEntity<StudentRegResp> registerStudent(StudentRequest studentRequest) {
		 String result=studentService.registerStudent(studentRequest);
		 StudentRegResp response = new StudentRegResp(result);
	     if(result.equals("OTP sent to the email successfully")) {
	    	 return ResponseEntity.ok(response);
	     }
	     return ResponseEntity.badRequest().body(response);
	 }

	@Override
	public ResponseEntity<StudentRegResp> OTPVerify(EmailRequest emailRequest) {
		try {
			String response =studentService.OTPVerify(emailRequest);
			StudentRegResp res=new StudentRegResp(response);
			if (response.equals("Student registered successfully")) {
		        return ResponseEntity.ok(res);
			}
			else {
				return ResponseEntity.badRequest().body(res);
			}
			
		}
		catch (Exception e) {
			StudentRegResp response = new StudentRegResp("OTP verification Failed: " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<List<GetAllStudentsResp>> getStudentsByParam(Long rollNo, String firstName) {
		List<GetAllStudentsResp> response =studentService.getStudentsByParam(rollNo, firstName);
		if(response.size()>0) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}

	@Override
	public ResponseEntity<StudentRegResp> updateStudent(UpdateStudentRequest updateStudentRequest) {
		 String result=studentService.updateStudent(updateStudentRequest);
		 StudentRegResp response = new StudentRegResp(result);
	     if(result.equals("Student Updated Successfully")) {
	    	 return ResponseEntity.ok(response);
	     }
	     return ResponseEntity.badRequest().body(response);
	}

	@Override
	public ResponseEntity<StudentRegResp> deleteStudent(Long studentId) {
		boolean result =studentService.deleteStudent(studentId);
		StudentRegResp studentRegResp;
		if(result) {
			String response="Student Deleted Successfully";
			studentRegResp=new StudentRegResp(response);
			return ResponseEntity.ok(studentRegResp);
		}
		else {
			String response="Student not found with the given Id: "+studentId;
			studentRegResp=new StudentRegResp(response);
			return ResponseEntity.ok(studentRegResp);
		}
	}

}
