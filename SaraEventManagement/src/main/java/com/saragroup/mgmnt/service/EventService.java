package com.saragroup.mgmnt.service;

import java.util.List;

import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Event;

public interface EventService {

	public boolean publishEvent(Event event) throws EventServiceException;

	List<Event> fetchAllEvents() throws EventServiceException;
}
