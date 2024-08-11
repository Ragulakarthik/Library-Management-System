package com.lms.response;

import lombok.Data;

@Data
public class PublisherRegResp {
    private String message;

    public PublisherRegResp(String message) {
        this.message = message;
    }
}
