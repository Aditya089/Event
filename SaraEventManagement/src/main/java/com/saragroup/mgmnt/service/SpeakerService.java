package com.saragroup.mgmnt.service;

import java.util.List;

import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.model.Speaker;

public interface SpeakerService {

	public boolean registerSpeaker(Speaker speaker) throws EventServiceException;

	List<Speaker> fetchSpeakers() throws EventServiceException;

	Speaker fetchSpeakerById(String speakerId) throws EventServiceException;

	Speaker fetchSpeakerByName(String name) throws EventServiceException;
}
