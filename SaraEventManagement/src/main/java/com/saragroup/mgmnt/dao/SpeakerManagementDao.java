package com.saragroup.mgmnt.dao;

import java.util.List;

import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.model.Speaker;

public interface SpeakerManagementDao {
	
	public boolean createNewSpeaker(Speaker speaker);

	public List<Speaker> fetchSpeakers();

	public Speaker fetchDetailsBySpeakerId(String speakerId);

	public Speaker getSpeakerByName(String sName);
}
