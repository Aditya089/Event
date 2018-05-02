package com.saragroup.mgmnt.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.saragroup.mgmnt.exception.AuthenticationException;
import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.helper.EncryptionUtility;
import com.saragroup.mgmnt.model.AuthorityName;
import com.saragroup.mgmnt.model.Login;
import com.saragroup.mgmnt.model.User;
import com.saragroup.mgmnt.service.LoginService;
import com.saragroup.mgmnt.validators.UserValidator;

@Controller
public class LoginController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		LOGGER.info("Login request received..");
		User userDetails = new User();;
		ModelAndView mv = new ModelAndView();
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        
        if(flashMap!= null && flashMap.get("error") !=null) {
        	String error =  (String) flashMap.get("error");
        	mv.addObject("error",error);
        }
		mv.addObject("loginform", userDetails);
		mv.setViewName("LoginPage");
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest request, @ModelAttribute("loginform") @Valid Login loginObj,
			BindingResult bindingResult, ModelAndView modelMap, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			LOGGER.info("Validation failed.. Binding errror found while registering user.");
			return new ModelAndView("LoginPage");
		}
		String result = "redirect:/login";
		User userDetails = null;
		
		try {
			loginObj.setPassword(EncryptionUtility.encrypt(loginObj.getPassword()));
			userDetails = loginService.getUserDetails(loginObj);
			
			request.getSession().setAttribute("user", userDetails);
			request.getSession().setAttribute("userlogged", "Y");

			result = "redirect:/home";
		} catch (AuthenticationException e) {
			redirectAttributes.addFlashAttribute("error", "User doesn't exist. Provide proper credential.");
			LOGGER.fatal("Exception Occured in login service", e);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Log in error. Try again.");
			LOGGER.fatal("Exception Occured in login service", e);
		}

		modelMap.setViewName(result);

		return modelMap;
	}

	@PostMapping("/signup")
	public String registerUser(@ModelAttribute("userform") @Valid User user, BindingResult bindingResult,
			Model model) {

		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			LOGGER.info("Validation failed.. Binding errror found while registering user.");
			return "registration";
		}

		try {
			user.setPassword(EncryptionUtility.encrypt(user.getPassword()));
			user.setCreationDate(new Date());
			user.setAuthorities(new ArrayList<AuthorityName>(Arrays.asList(AuthorityName.ROLE_ADMIN)));
			
			loginService.registerUser(user);
		} catch (EventServiceException e) {
			LOGGER.fatal("Exception Occured in login service", e);
		}

		return "redirect:/login";
	}

	@GetMapping("/signup")
	public ModelAndView registerUser() {
		ModelAndView mv = new ModelAndView("registration");
		mv.addObject("userform", new User());
		return mv;
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login";
	}
}
