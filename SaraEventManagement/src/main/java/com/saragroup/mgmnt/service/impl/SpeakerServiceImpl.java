package com.saragroup.mgmnt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saragroup.mgmnt.dao.SpeakerManagementDao;
import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Speaker;
import com.saragroup.mgmnt.service.SpeakerService;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {

	@Autowired
	SpeakerManagementDao speakerDao;
	
	@Override
	public boolean registerSpeaker(Speaker speaker) throws EventServiceException {
		speakerDao.createNewSpeaker(speaker);
		return true;
	}
	
	@Override
	public List<Speaker> fetchSpeakers() throws EventServiceException {
		return speakerDao.fetchSpeakers();
	}

}
