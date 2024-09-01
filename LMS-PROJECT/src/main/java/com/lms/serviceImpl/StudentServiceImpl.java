package com.lms.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lms.common.exception.ExtractMessage;
import com.lms.common.exception.LmsException;
import com.lms.entity.Otp;
import com.lms.entity.Student;
import com.lms.repository.OtpRepo;
import com.lms.repository.StudentRepo;
import com.lms.request.EmailRequest;
import com.lms.request.StudentRequest;
import com.lms.request.UpdateStudentRequest;
import com.lms.response.GetAllStudentsResp;
import com.lms.service.IOtpService;
import com.lms.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private IOtpService otpService;
	
	@Autowired
	private ExtractMessage extractMessage;
	
	@Autowired
	private OtpRepo otpRepo;
    
    private Student student;

	@Override
	public String registerStudent(StudentRequest studentRequest) {
		try {
			if(!studentRequest.password.equals(studentRequest.confirmPassword)) {
				return "Password Do not match";
			}
			student=new Student();
			student.setFirstName(studentRequest.firstName);
			student.setLastName(studentRequest.lastName);
			student.setEmail(studentRequest.email);
			student.setPhoneNumber(studentRequest.phoneNumber);
			student.setBranch(studentRequest.getBranch());
			student.setCourse(studentRequest.getCourse());
			student.setPassword(studentRequest.password);
			student.setRegistredDate(new Date());
			otpService.sendOtp(studentRequest.email);
            
            return "OTP sent to the email successfully";
		}
		catch (Exception e) {
			throw new LmsException(e.getMessage());
		}
	}

	@Override
    public String OTPVerify(EmailRequest emailRequest) {
        try {
            Optional<Otp> otpEntityOpt = otpRepo.findLatestOtpByEmail(emailRequest.email);
            if (otpEntityOpt.isEmpty()) {
                return "Email did not match with the registered email or OTP expired";
            }

            Otp otpEntity = otpEntityOpt.get();
            if (!otpEntity.getOtp().equals(emailRequest.otp)) {
                return "Invalid OTP";
            }

            if (otpEntity.getExpirationTime() < System.currentTimeMillis()) {
                return "OTP expired";
            }

            studentRepo.save(student);
            otpRepo.delete(otpEntity); // Delete OTP after successful verification
            return "Student registered successfully";
        } catch (DataIntegrityViolationException e) {
            String errorMessage = String.format(e.getMessage());
            String errorResponse = extractMessage.extractErrorMessage(errorMessage);
            throw new LmsException(errorResponse);
        }
    }

	@Override
	public List<GetAllStudentsResp> getStudentsByParam(Long rollNo,String firstName) {
		try {
			List<Student> students;
			if(rollNo==null && firstName==null) {
				students = studentRepo.findAll();
				if(students.isEmpty()) {
					throw new LmsException("No students found");
				}
			}
			else if(rollNo!=null && firstName==null) {
				Optional<Student> li=studentRepo.findById(rollNo);
				if(li.isEmpty()) {
					throw new LmsException("No students found with the rollNo : "+rollNo);
				}
				students= li.stream().collect(Collectors.toList());
			}
			else if(rollNo==null && firstName!=null) {
				students=studentRepo.getStudentByName(firstName);
				if(students.isEmpty()) {
					throw new LmsException("No students found with the given name :"+firstName);
				}
			}
			else{
				students=studentRepo.getStudentByRollName(rollNo,firstName);
				if(students.isEmpty()) {
					throw new LmsException("No students found with the given Details");
				}
			}
			List<GetAllStudentsResp> getAllStudentsResp=new ArrayList<>();
			for(Student student: students) {
				getAllStudentsResp.add(new GetAllStudentsResp(student));
			}
			return getAllStudentsResp;
		}
		catch (NoSuchElementException e) {
			throw new LmsException(e.getMessage()+" :No student found");
		}
	}

	@Override
	public String updateStudent(UpdateStudentRequest updateStudentRequest) {
		try {
			Optional<Student> stu=studentRepo.findById(updateStudentRequest.getRollNo());
			if(stu.isEmpty()) {
				throw new LmsException("No student found with this RollNo : "+updateStudentRequest.getRollNo());
			}
			if(!stu.get().getEmail().equals(updateStudentRequest.getEmail())) {
				throw new LmsException("Email cannot be changed once registred");
			}
			Student st=stu.get();
			st.updateStudent(updateStudentRequest);
			studentRepo.save(st);
			return "Student Updated Successfully";
		}
		catch(Exception e) {
			throw new LmsException(e.getMessage());
		}
	}

	@Override
	public boolean deleteStudent(Long studentId) {
		try {
			Optional<Student> student=studentRepo.findById(studentId);
			if(student.isEmpty()) {
				return false;
			}
			studentRepo.deleteById(studentId);
			return true;
		}
		catch (Exception e) {
			throw new LmsException(e.getMessage());
		}
	}
}
