package com.saragroup.mgmnt.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saragroup.mgmnt.dao.SpeakerManagementDao;
import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Speaker;
import com.saragroup.mgmnt.service.SpeakerService;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {

	private static final Logger LOGGER = Logger.getLogger(SpeakerServiceImpl.class);
	
	@Autowired
	SpeakerManagementDao speakerDao;
	
	@Override
	public boolean registerSpeaker(Speaker speaker) throws EventServiceException {
		LOGGER.info("Inside Speaker Service. Registering new Speaker." + speaker.getName());
		return speakerDao.createNewSpeaker(speaker);
	}
	
	@Override
	public List<Speaker> fetchSpeakers() throws EventServiceException {
		LOGGER.info("Fetching all resgistered speakers.");
		List<Speaker> speakerList = speakerDao.fetchSpeakers();
		LOGGER.info("Total Number of Speakers found: " + speakerList.size());
		return speakerList;
	}

	@Override
	public Speaker fetchSpeakerById(String speakerId) throws EventServiceException {
		LOGGER.info("Fetching Speaker by Id." + speakerId);
		return speakerDao.fetchDetailsBySpeakerId(speakerId);
	}
	
	@Override
	public Speaker fetchSpeakerByName(String sName) throws EventServiceException {
		LOGGER.info("Fetching Speaker by Name." + sName);
		return speakerDao.getSpeakerByName(sName);
	}

}
