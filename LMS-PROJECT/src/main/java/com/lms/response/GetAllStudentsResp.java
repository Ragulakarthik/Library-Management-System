package com.lms.response;

import com.lms.entity.Student;

import lombok.Data;

@Data
public class GetAllStudentsResp {
	
	private long rollno;
	
	private String firstName;

	private String lastName;

	private String email;
	
	private String course;
	
	private String branch;

	private String password;
	
	private String phoneNumber;
	
	public GetAllStudentsResp(long rollno,String firstName, String lastName, String email, String password, String phoneNumber,String course,String branch) {
		this.rollno=rollno;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber =phoneNumber;
        this.branch=branch;
        this.course=course;
    }
	
	public GetAllStudentsResp(Student student) {
		this.rollno=student.getRollNo();
		this.firstName=student.getFirstName();
		this.lastName=student.getLastName();
		this.email=student.getEmail();
		this.phoneNumber=student.getPhoneNumber();
		this.password=student.getPassword();
		this.branch=student.getBranch();
		this.course=student.getCourse();
	}
}