package com.saragroup.mgmnt.controller;

import javax.servlet.http.HttpServletRequest;

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

		@Autowired
		SpeakerService speakerService;

		@PostMapping("/speaker/create")
		public String registerSpeaker(HttpServletRequest request, @ModelAttribute("SpeakerForm") Speaker speaker,  BindingResult bindingResult,
				Model model) {

			try {
				speakerService.registerSpeaker(speaker);
			} catch (EventServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			return "redirect:/login";
		}
}
