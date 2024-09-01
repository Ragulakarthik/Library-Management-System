package com.lms.request;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Validated
public class StudentRequest {

	@NotEmpty(message = "firstName cannot be empty")
	@Pattern(regexp="^([A-Za-z]{1,25}( [A-Za-z]{1,25}){0,24})?$", message="firstName should contain only alphabet and size should not exceed 25 characters.")
    public String firstName;

	@NotEmpty(message = "lastName cannot be empty")
	@Pattern(regexp="^([A-Za-z]{1,25}( [A-Za-z]{1,25}){0,24})?$", message="lastName should contain only alphabet and size should not exceed 25 characters.")
    public String lastName;

	@NotEmpty(message = "email cannot be empty.")
	@Pattern(regexp="^[a-zA-Z0-9._%+-]{3,30}+@gmail\\.com$",message="Invalid Email Id")
    public String email;

	@NotEmpty(message="Phone Number cannot be empty")
	@Pattern(regexp="^[6-9]\\d{9}$", message="Phone Number can only start with 6,7,8,9 and it should contain 10 digits")
	public String phoneNumber;
	
	@NotEmpty(message = "course cannot be empty")
	@Pattern(regexp="^(B.Tech|M.Tech|MCA|MBA|Pharmacy)$", message="Enter a valid course code such as B.Tech, M.Tech, MCA, MBA, Pharmacy")
    public String course;
	
	@NotEmpty(message = "branch cannot be empty")
	@Pattern(regexp="^(CSE|CSEDS|CSEAIML|CSEAI|IT|EEE|ECE|CIVIL|MECH)$", message="Enter a valid branch code such as CSE, IT, EEE, ECE, CIVIL, MECH, CSEDS, CSEAI, CSEAIML")
    public String branch;
	
	@NotEmpty(message = "password cannot be empty.")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.,])[A-Za-z\\d@$!%*?&.,]{8,}$", message="1) At least one lowercase letter.\n2) At least one uppercase letter.\n3) At least one digit.\n4) At least one special character.\n5) The password is at least 8 characters long and contains only the specified characters.")
    public String password;

	@NotEmpty(message = "confirmPassword cannot be empty.")
    public String confirmPassword;
}
