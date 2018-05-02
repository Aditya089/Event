package com.saragroup.mgmnt.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
		String id = getNewSpeakerId();
		speaker.setSpeakerId(id);
		mongoTemplate.save(speaker);
		LOGGER.info("successfully inserted record.."+ speaker);
		return true;
	}

	private String getNewSpeakerId() {
		Query query =  new Query();
		return "" +mongoTemplate.count(query, Speaker.class) + 1;
	}

	@Override
	public List<Speaker> fetchSpeakers() {
		if(mongoTemplate.collectionExists(Speaker.class)) {
			return mongoTemplate.findAll(Speaker.class);
		}
		return null;
	}

	@Override
	public Speaker fetchDetailsBySpeakerId(String speakerId) {
		Query query = new Query();
		return mongoTemplate.findOne(query.addCriteria(Criteria.where("speakerNum").is(speakerId)),
				Speaker.class);
	}

	@Override
	public Speaker getSpeakerByName(String sName) {
		Query query = new Query();
		return mongoTemplate.findOne(query.addCriteria(Criteria.where("name").is(sName)),
				Speaker.class);
	}


}
