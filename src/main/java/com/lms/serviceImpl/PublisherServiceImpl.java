package com.lms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lms.common.exception.ExtractMessage;
import com.lms.common.exception.LmsException;
import com.lms.entity.Publisher;
import com.lms.repository.PublisherRepo;
import com.lms.service.IPublisherService;

@Service
public class PublisherServiceImpl implements IPublisherService {

	@Autowired
	private PublisherRepo publisherRepo;
	
	@Autowired
	private ExtractMessage extractMessage;
	
	@Override
	public boolean registerPublisher(String publisherName, String phoneNumber) {
		try {
			Publisher publisher=new Publisher();
			publisher.setPublisherName(publisherName);
			publisher.setPhoneNumber(phoneNumber);
			publisherRepo.save(publisher);
			return true;
		}
		catch (DataIntegrityViolationException e) {
			String errorMessage=String.format(e.getMessage());
			String errorResponse = extractMessage.extractErrorMessage(errorMessage);
			throw new LmsException(errorResponse);
		}
		catch (Exception e) {
			return false;
		}
	}
}
