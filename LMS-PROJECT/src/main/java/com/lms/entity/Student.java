package com.lms.entity;

import java.util.Date;

import org.springframework.data.relational.core.mapping.Table;

import com.lms.request.UpdateStudentRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rollNo;
    
    @Column(name = "first_name", nullable = false)
    @NotEmpty
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    @NotEmpty
    private String lastName;
    
    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    private String email;
    
    @Column(name= "phoneNumber", nullable=false, unique=true)
    @NotEmpty
    private String phoneNumber;
    
    @Column(name= "course", nullable=false)
    @NotEmpty
    private String course;
    
    @Column(name= "branch", nullable=false)
    @NotEmpty
    private String branch;
    
    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;
    
    @Column(name="registredDate",nullable =false)
    @NotNull
    private Date registredDate;
    
    public void updateStudent(UpdateStudentRequest updateStudentRequest) {
    	this.firstName=updateStudentRequest.getFirstName();
    	this.lastName=updateStudentRequest.getLastName();
    	this.password=updateStudentRequest.getPassword();
    	this.phoneNumber=updateStudentRequest.getPhoneNumber();
    	this.branch=updateStudentRequest.getBranch();
    	this.course=updateStudentRequest.getCourse();
    }
}