package com.saragroup.mgmnt.dao;

import java.util.List;

import com.saragroup.mgmnt.model.Speaker;

public interface SpeakerManagementDao {
	public boolean createNewSpeaker(Speaker speaker);

	public List<Speaker> fetchSpeakers(); 
}
