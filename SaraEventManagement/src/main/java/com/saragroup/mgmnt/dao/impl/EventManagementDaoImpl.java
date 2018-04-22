package com.saragroup.mgmnt.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;


import com.saragroup.mgmnt.dao.EventManagementDao;
import com.saragroup.mgmnt.model.Event;

@Repository("eventDao")
public class EventManagementDaoImpl implements EventManagementDao{
	private final Logger LOGGER = Logger.getLogger(EventManagementDaoImpl.class);
	
	@Autowired
	MongoOperations mongoTemplate;

	@Override
	public boolean publishEvent(Event event) {
		mongoTemplate.insert(event);
		LOGGER.info("successfully inserted record.."+ event);
		return true;
	}

	@Override
	public List<Event> fetchAllEvents() {
		if(mongoTemplate.collectionExists(Event.class)) {
			return mongoTemplate.findAll(Event.class);
		}
		return null;
	}

}
