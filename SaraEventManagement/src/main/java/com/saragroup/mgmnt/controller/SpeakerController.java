package com.saragroup.mgmnt.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Speaker;
import com.saragroup.mgmnt.service.SpeakerService;

@Controller
public class SpeakerController {
	
	private static final Logger LOGGER = Logger.getLogger(SpeakerController.class);

	@Autowired
	SpeakerService speakerService;

	@PostMapping("/speaker/create")
	public String registerSpeaker(HttpServletRequest request, @ModelAttribute("SpeakerForm") Speaker speaker,
			BindingResult bindingResult, Model model) {
		
		LOGGER.info("New Speaker request received. Speaker name:" + speaker.getName());

		try {
			speakerService.registerSpeaker(speaker);
			LOGGER.info("Successfully registered the speaker. Generated Id:" + speaker.getDocumentId());
		} catch (EventServiceException e) {
			LOGGER.fatal("Exception Occured.. " , e);
			model.addAttribute("error", "Problem while registering Speaker: " + speaker.getName());
		}

		return "redirect:/home";
	}
}
