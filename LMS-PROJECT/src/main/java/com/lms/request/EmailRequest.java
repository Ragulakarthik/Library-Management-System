package com.lms.request;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Validated
public class EmailRequest {
	@NotEmpty(message = "email cannot be empty.")
	@Pattern(regexp="^[a-zA-Z0-9._%+-]{3,30}+@gmail\\.com$",message="Invalid Email Id")
    public String email;
	
	@NotEmpty(message= "OTP cannot be empty")
	public String otp;
}
