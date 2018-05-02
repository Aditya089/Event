package com.saragroup.mgmnt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.model.Speaker;
import com.saragroup.mgmnt.model.User;
import com.saragroup.mgmnt.service.EventService;
import com.saragroup.mgmnt.service.SpeakerService;

@Controller
public class HomeController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	SpeakerService speakerService;

	@GetMapping("/home")
	public ModelAndView login(HttpServletRequest request,
			ModelAndView modelMap) {
		
		String result = "userhome";
		
		String userLoggedAlready = (String) request.getSession().getAttribute("userlogged");
		User userDetails = null;
		if (!StringUtils.isEmpty(userLoggedAlready) && "Y".equalsIgnoreCase(userLoggedAlready)) {
			userDetails = (User) request.getSession().getAttribute("user");
			
			try {
				
				modelMap.addObject("EventForm", new Event());
				modelMap.addObject("userDtls", userDetails);
				modelMap.addObject("SpeakerForm", new Speaker());
				
				List<Event> allEvents= eventService.fetchAllEvents(); 
				modelMap.addObject("AllEvents", allEvents);
				
				List<String> availabletopics = new ArrayList<String>();
				availabletopics.add("Java");
				availabletopics.add("Data Science");
				availabletopics.add("Oracle");
				availabletopics.add("Automation Testing");
				availabletopics.add("Hadoop");
				modelMap.addObject("availableTopics", availabletopics);
				
				// Add dynamic data to the Hobbies list
				List<Speaker> availableSpeakers = speakerService.fetchSpeakers();
				/*availableSpeakers.add("John Doe");
				availableSpeakers.add("Mavin Maverick");
				availableSpeakers.add("Jack Daniels");
				availableSpeakers.add("Joe West");*/
				modelMap.addObject("availableSpeakers", availableSpeakers);
				
				
				List<Event> allRegEvents= eventService.fetchAllSubscribedEvents(userDetails.getUsername()); 
				modelMap.addObject("SubscribedEvents", allRegEvents);
				
			} catch (EventServiceException e) {
		
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
		
			result = "redirect:/login";
		}
		
		modelMap.setViewName(result);

		return modelMap;
	}}
