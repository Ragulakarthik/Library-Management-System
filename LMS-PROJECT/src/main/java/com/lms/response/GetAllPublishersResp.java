package com.lms.response;

import com.lms.entity.Publisher;

import lombok.Data;

@Data
public class GetAllPublishersResp {

	private long publisherId;
	
	private String publisherName;
	
	private String phoneNumber;
	
	public GetAllPublishersResp(long publisherId, String publisherName, String phoneNumber) {
		this.publisherId=publisherId;
		this.publisherName=publisherName;
		this.phoneNumber=phoneNumber;
	}
	public GetAllPublishersResp(Publisher pub) {
		this.publisherId=pub.getPublisherId();
		this.publisherName=pub.getPublisherName();
		this.phoneNumber=pub.getPhoneNumber();
	}
}
