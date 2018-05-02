package com.saragroup.mgmnt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.model.User;
import com.saragroup.mgmnt.service.EventService;

@Controller
public class EventController {
	
	private static final Logger LOGGER = Logger.getLogger(EventController.class);
	
	@Autowired
	EventService eventService;

	@PostMapping("/event/create")
	public String createEvent(HttpServletRequest request, @ModelAttribute("EventForm") @Valid Event event,  BindingResult bindingResult,
			Model model) {
		LOGGER.info("New Event request received. Event name: " + event.getEventName());
	
		try {
			eventService.publishEvent(event);
			LOGGER.info("Event Successfully Created.. Event Id." + event.getDocumentId());
		} catch (EventServiceException e) {
			LOGGER.fatal("Exception Occured.. " , e);
			model.addAttribute("error", "Problem while creating Event: " + event.getEventName());
		}
		
		return "redirect:/home";
	}
	
	@GetMapping(value = "/event/subscribe/{eventName}") 
	public String subscribeEvent(HttpServletRequest request,
			@PathVariable(value = "eventName") String eventName) {
		User userDetails = (User) request.getSession().getAttribute("user");

		if(userDetails != null) {
			LOGGER.info("Subscribe request received for Event:" + eventName + ", User: " + userDetails.getUsername());
			eventService.subscribeEvent(userDetails.getUsername(), eventName);
		}
				return "redirect:/home";
		
	}
	
	@GetMapping(value = "/event/unsubscribe/{eventName}") 
	public String unSubscribeEvent(HttpServletRequest request,
			@PathVariable(value = "eventName") String eventName) {
		User userDetails = (User) request.getSession().getAttribute("user");

		if(userDetails != null) {
			LOGGER.info("Unsubscribe request received for Event:" + eventName + ", User: " + userDetails.getUsername());
			eventService.unSubscribeEvent(userDetails.getUsername(), eventName);
		}
				return "redirect:/home";
		
	}
}
