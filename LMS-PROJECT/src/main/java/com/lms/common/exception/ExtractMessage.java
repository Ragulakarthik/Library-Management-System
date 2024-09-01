package com.lms.common.exception;

import org.springframework.stereotype.Component;

@Component
public class ExtractMessage {
	
	public String extractErrorMessage(String input) {
        int startIndex = input.indexOf('[');
        int endIndex = input.indexOf(']', startIndex);
        if (startIndex != -1 && endIndex != -1) {
            return input.substring(startIndex + 1, endIndex);
        }
        return "";
    }
}
