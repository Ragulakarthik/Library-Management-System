package com.lms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lms.entity.Otp;
import com.lms.repository.OtpRepo;
import com.lms.service.IOtpService;

import jakarta.transaction.Transactional;

import java.security.SecureRandom;

@Service
@Transactional
public class OtpServiceImpl implements IOtpService{
	
	private static final SecureRandom secureRandom = new SecureRandom();
    private static final int OTP_LENGTH = 6;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private OtpRepo otpRepo;

	private String generateOtp() {
		int min = (int) Math.pow(10, OTP_LENGTH - 1); // 100000
        int max = (int) Math.pow(10, OTP_LENGTH) - 1; // 999999
        int otp = secureRandom.nextInt(max - min + 1) + min;
        return String.valueOf(otp);
	}
	
	private void storeOtpInDatabase(String email, String otp) {
		otpRepo.deleteAllByEmail(email);
		Long expiryTime = System.currentTimeMillis() + 10 * 60 * 1000; // Example expiry time
	    Otp otpEntity = new Otp(email, otp, expiryTime);
	    otpRepo.save(otpEntity);
    }

	@Override
	public String sendOtp(String email) {
		String otp=generateOtp();
		storeOtpInDatabase(email, otp);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("OTP VERIFICATION");
        message.setTo(email);
        String body="OTP for login is : "+otp;
        message.setText(body);
        mailSender.send(message);
        return otp;
	}

	@Override
	public String sendIssueBookMessage(String email,String message,String sub) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject(sub);
        msg.setTo(email);
        String body=message;
        msg.setText(body);
        mailSender.send(msg);
        return message;
	}
}
