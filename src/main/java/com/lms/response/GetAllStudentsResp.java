package com.lms.response;

import lombok.Data;

@Data
public class GetAllStudentsResp {
	
	private long rollno;
	
	private String firstName;

	private String lastName;

	private String email;

	private String password;
	
	private String phoneNumber;
	
	public GetAllStudentsResp(long rollno,String firstName, String lastName, String email, String password, String phoneNumber) {
		this.rollno=rollno;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber =phoneNumber;
    }
}