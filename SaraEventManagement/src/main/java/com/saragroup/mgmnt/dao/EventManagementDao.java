package com.saragroup.mgmnt.dao;

import java.util.List;

import com.saragroup.mgmnt.model.Event;

public interface EventManagementDao {
	public boolean publishEvent(Event event);

	public List<Event> fetchAllEvents();

	public void subscribeEvent(String fullName, String eventName);

	public List<Event> fetchAllSubscribedEvents(String fullName);

	public void unSubscribeEvent(String username, String eventName); 
}
