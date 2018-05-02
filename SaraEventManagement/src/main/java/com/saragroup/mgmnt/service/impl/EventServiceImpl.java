package com.saragroup.mgmnt.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saragroup.mgmnt.dao.EventManagementDao;
import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.model.Speaker;
import com.saragroup.mgmnt.service.EventService;
import com.saragroup.mgmnt.service.SpeakerService;

@Service("eventService")
public class EventServiceImpl implements EventService {
	
	private static final Logger LOGGER = Logger.getLogger(EventServiceImpl.class);

	@Autowired
	EventManagementDao eventDao;
	
	@Autowired
	SpeakerService speakerService;
	
	@Override
	public boolean publishEvent(Event event) throws EventServiceException {
		LOGGER.info("Inside Event Service. Publishing the Event..");
		if(event != null) {
			eventDao.publishEvent(event);
		}
		return true;
	}
	
	@Override
	public List<Event> fetchAllEvents() throws EventServiceException {
		LOGGER.info("Inside Event Service. Retrieving all available events..");
		List<Event> eventList  = eventDao.fetchAllEvents();
		if(eventList!=null)
		LOGGER.info("Number of events Found: " + eventList.size());
		
		for (Event event : eventList) {
				Speaker speaker= speakerService.fetchSpeakerById(event.getSpeakerId());
				event.setSpeaker(speaker);
		}
		return eventList;
	}

	@Override
	public void subscribeEvent(String fullName, String eventName) {
		eventDao.subscribeEvent(fullName, eventName);
		
	}
	
	@Override
	public List<Event> fetchAllSubscribedEvents(String fullName){
		LOGGER.info("Inside Event Service. Retrieving all registered the Event..");
		List<Event> eventList  = eventDao.fetchAllSubscribedEvents(fullName);
		if(eventList!=null)
		LOGGER.info("Number of events Found: " + eventList.size());
		
		return eventList;
	}

	@Override
	public void unSubscribeEvent(String username, String eventName) {
		eventDao.unSubscribeEvent(username, eventName);
	}

}
