package com.lms.response;

import lombok.Data;

@Data
public class GetAllPublishers {

	private long publisherId;
	
	private String publisherName;
	
	private String phoneNumber;
	
	public GetAllPublishers(long publisherId, String publisherName, String phoneNumber) {
		this.publisherId=publisherId;
		this.publisherName=publisherName;
		this.phoneNumber=phoneNumber;
	}
}
