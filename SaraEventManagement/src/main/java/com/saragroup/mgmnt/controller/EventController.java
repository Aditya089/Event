package com.saragroup.mgmnt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.service.EventService;

@Controller
public class EventController {
	@Autowired
	EventService eventService;

	@PostMapping("/event/create")
	public String createEvent(HttpServletRequest request, @ModelAttribute("EventForm") Event event,  BindingResult bindingResult,
			Model model) {

		try {
			eventService.publishEvent(event);
		} catch (EventServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return "redirect:/login";
	}
	
}
