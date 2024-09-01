package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.PublisherApi;
import com.lms.response.GetAllPublishersResp;
import com.lms.response.PublisherRegResp;
import com.lms.service.IPublisherService;


@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class PublisherController implements PublisherApi {

	@Autowired
	private IPublisherService publisherService;

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
	public ResponseEntity<List<GetAllPublishersResp>> getPublishersByParam(Long publisherId,String publisherName){
		List<GetAllPublishersResp> response=publisherService.getPublishersByParam(publisherId,publisherName);
		if(response.size()>0) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}

	@Override
	public ResponseEntity<PublisherRegResp> updatePublisher(Long publisherId,String publisherName,String phoneNumber) {
		boolean result=publisherService.updatePublisher(publisherId,publisherName,phoneNumber);
		if(result==true) {
			PublisherRegResp response=new PublisherRegResp("Publisher updated successfully");
			return ResponseEntity.ok(response);
		}
		else {
			PublisherRegResp response=new PublisherRegResp("Failed to update publisher");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<PublisherRegResp> deletePublisher(Long publisherId) {
		boolean result=publisherService.deletePublisher(publisherId);
		if(result==true) {
			PublisherRegResp response=new PublisherRegResp("publisher deleted successfully");
			return ResponseEntity.ok(response);
		}
		else {
			PublisherRegResp response=new PublisherRegResp("Publisher not found with the given Id: "+publisherId);
			return ResponseEntity.badRequest().body(response);
		}
	}
}
