package com.saragroup.mgmnt.service;

import java.util.List;

import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Event;

public interface EventService {

	public boolean publishEvent(Event event) throws EventServiceException;

	public List<Event> fetchAllEvents() throws EventServiceException;

	public void subscribeEvent(String fullName, String eventId);

	public List<Event> fetchAllSubscribedEvents(String fullName);

	public void unSubscribeEvent(String username, String eventName);
}
