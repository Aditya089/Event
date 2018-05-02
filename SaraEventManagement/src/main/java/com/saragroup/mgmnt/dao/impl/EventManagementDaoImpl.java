package com.saragroup.mgmnt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import com.saragroup.mgmnt.dao.EventManagementDao;
import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.model.User;

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

	@Override
	public void subscribeEvent(String fullName, String eventName) {
		User temp= mongoTemplate.findOne(new Query().addCriteria(Criteria.where("username").is(fullName) ), User.class);
		temp.addEvents(eventName);
		temp.setRePassword("ENCRYPTED");
		mongoTemplate.save(temp);
	}

	@Override
	public List<Event> fetchAllSubscribedEvents(String fullName) {
		List<Event> regEvents = null;
		if(mongoTemplate.collectionExists(Event.class)) {
			User temp= mongoTemplate.findOne(new Query().addCriteria(Criteria.where("username").is(fullName) ), User.class);
			List<String> eventIds = temp.getEvents();
			if(eventIds!=null) {
				regEvents = new ArrayList<Event>();
				for (String eventId : eventIds) {
					Event event = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("eventName").is(eventId) ), Event.class);
					regEvents.add(event);
				}
			}
		}
		return regEvents;
	}

	@Override
	public void unSubscribeEvent(String username, String eventName) {
		User temp= mongoTemplate.findOne(new Query().addCriteria(Criteria.where("username").is(username) ), User.class);
		if(temp.getEvents().contains(eventName)) {
			temp.getEvents().remove(eventName);
		}
		
		temp.setRePassword("ENCRYPTED");
		mongoTemplate.save(temp);
		LOGGER.info("Successfully Removed event" + eventName);
	}
}
