package com.lms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lms.common.exception.ExtractMessage;
import com.lms.common.exception.LmsException;
import com.lms.entity.Publisher;
import com.lms.repository.PublisherRepo;
import com.lms.response.GetAllPublishersResp;
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

	@Override
	public boolean updatePublisher(Long publisherId, String publisherName, String phoneNumber) {
		try {
			Optional<Publisher> publisher=publisherRepo.findById(publisherId);
			if(publisher.isEmpty()) {
				throw new LmsException("Publisher with given id:"+publisherId+" not found");
			}
			Publisher pub=publisher.get();
			pub.updatePublisher(publisherName, phoneNumber);
			publisherRepo.save(pub);
			return true;
		}
		catch (DataIntegrityViolationException e) {
			String errorMessage=String.format(e.getMessage());
			String errorResponse = extractMessage.extractErrorMessage(errorMessage);
			throw new LmsException(errorResponse);
		}
		catch (Exception e) {
			throw new LmsException(e.getMessage());
		}
	}

	@Override
	public List<GetAllPublishersResp> getPublishersByParam(Long publisherId, String publisherName) {
		List<Publisher> publisher;
		if(publisherId==null && publisherName==null) {
			publisher=publisherRepo.findAll();
			if(publisher.isEmpty()) {
				throw new LmsException("No data found");
			}
		}
		else if(publisherId!=null && publisherName==null) {
			Optional<Publisher> pub;
			pub=publisherRepo.findById(publisherId);
			if(pub.isEmpty()) {
				throw new LmsException("No data found with the given Id :"+publisherId);
			}
			publisher=pub.stream().collect(Collectors.toList());
		}
		else if(publisherId==null && publisherName!=null) {
			publisher=publisherRepo.findByPublisherName(publisherName);
			if(publisher.isEmpty()) {
				throw new LmsException("No data found with the given name: "+publisherName);
			}
		}
		else {
			publisher=publisherRepo.findByPublisherIdAndPublisherName(publisherId,publisherName);
			if(publisher.isEmpty()) {
				throw new LmsException("No data found with the given id :"+publisherId+" ,name : "+publisherName);
			}
		}
		List<GetAllPublishersResp> getAllPublisherResp=new ArrayList<>();
		for(Publisher pub:publisher) {
			getAllPublisherResp.add(new GetAllPublishersResp(pub));
		}
		return getAllPublisherResp;
	}

	@Override
	public boolean deletePublisher(Long publisherId) {
		try {
			Optional<Publisher> publisher=publisherRepo.findById(publisherId);
			if(publisher.isEmpty()) {
				return false;
			}
			publisherRepo.deleteById(publisherId);
			return true;
		}catch (Exception e) {
			throw new LmsException(e.getMessage());
		}
	}
}
