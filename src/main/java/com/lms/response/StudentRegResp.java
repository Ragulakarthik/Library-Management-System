package com.lms.response;

import lombok.Data;

@Data
public class StudentRegResp {
    private String message;

    public StudentRegResp(String message) {
        this.message = message;
    }
}
