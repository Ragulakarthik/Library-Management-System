package com.lms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.PublisherApi;
import com.lms.common.exception.LmsException;
import com.lms.entity.Publisher;
import com.lms.repository.PublisherRepo;
import com.lms.response.GetAllPublishers;
import com.lms.response.PublisherRegResp;
import com.lms.service.IPublisherService;


@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class PublisherController implements PublisherApi {

	@Autowired
	private IPublisherService publisherService;
	
	@Autowired
	private PublisherRepo publisherRepo;

	@Override
	public ResponseEntity<PublisherRegResp> registerPublisher(String publisherName,String phoneNumber) {
		boolean result=publisherService.registerPublisher(publisherName,phoneNumber);
		if(result==true) {
			PublisherRegResp response=new PublisherRegResp("Publisher added successfully");
			return ResponseEntity.ok(response);
		}
		else {
			PublisherRegResp response=new PublisherRegResp("Failed to add publisher");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<List<GetAllPublishers>> getAllPublishers() throws LmsException{
		List<Publisher> list=publisherRepo.findAll();
		List<GetAllPublishers> response =list.stream()
                .map(res -> new GetAllPublishers(
                		res.getPublisherId(),
                		res.getPublisherName(),
                		res.getPhoneNumber()))
                .collect(Collectors.toList());
		if(response.size()==0) {
			throw new LmsException("No data found");
		}
		return ResponseEntity.ok(response);
	}
}
