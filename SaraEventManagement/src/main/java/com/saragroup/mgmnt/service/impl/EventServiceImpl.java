package com.saragroup.mgmnt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saragroup.mgmnt.dao.EventManagementDao;
import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.service.EventService;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	EventManagementDao eventDao;
	
	@Override
	public boolean publishEvent(Event event) throws EventServiceException {
		eventDao.publishEvent(event);
		return true;
	}
	
	@Override
	public List<Event> fetchAllEvents() throws EventServiceException {
		return eventDao.fetchAllEvents();
	}
	

}
