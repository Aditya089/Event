package com.saragroup.mgmnt.dao;

import java.util.List;

import com.saragroup.mgmnt.model.Event;

public interface EventManagementDao {
	public boolean publishEvent(Event event);

	public List<Event> fetchAllEvents(); 
}
