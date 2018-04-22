package com.saragroup.mgmnt.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;


import com.saragroup.mgmnt.dao.SpeakerManagementDao;
import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.model.Speaker;

@Repository("speakerDao")
public class SpeakerManagementDaoImpl implements SpeakerManagementDao{
	private final Logger LOGGER = Logger.getLogger(SpeakerManagementDaoImpl.class);
	
	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public boolean createNewSpeaker(Speaker speaker) {
		mongoTemplate.save(speaker);
		LOGGER.info("successfully inserted record.."+ speaker);
		return true;
	}

	@Override
	public List<Speaker> fetchSpeakers() {
		if(mongoTemplate.collectionExists(Speaker.class)) {
			return mongoTemplate.findAll(Speaker.class);
		}
		return null;
	}

}
