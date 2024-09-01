package com.lms.service;

import java.util.List;

import com.lms.response.GetAllPublishersResp;

public interface IPublisherService {
	public boolean registerPublisher(String publisherName, String phoneNumber);
	
	public boolean updatePublisher(Long publisherId,String publisherName, String phoneNumber);
	
	public List<GetAllPublishersResp> getPublishersByParam(Long publisherid,String publisherName);

	public boolean deletePublisher(Long publisherId);
}
