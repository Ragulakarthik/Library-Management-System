package com.lms.response;

import lombok.Data;

@Data
public class AuthorRegResp {
	private String message;

    public AuthorRegResp(String message) {
        this.message = message;
    }
}
