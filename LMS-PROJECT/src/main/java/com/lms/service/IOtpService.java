package com.lms.service;

public interface IOtpService {
	
	public String sendOtp(String email);
	
	public String sendIssueBookMessage(String email,String message,String sub);
}
