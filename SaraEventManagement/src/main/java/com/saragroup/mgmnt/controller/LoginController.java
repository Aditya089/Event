package com.saragroup.mgmnt.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.saragroup.mgmnt.exception.EventMgmntDaoException;
import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.helper.EncryptionUtility;
import com.saragroup.mgmnt.model.AuthorityName;
import com.saragroup.mgmnt.model.Event;
import com.saragroup.mgmnt.model.Login;
import com.saragroup.mgmnt.model.Speaker;
import com.saragroup.mgmnt.model.User;
import com.saragroup.mgmnt.service.EventService;
import com.saragroup.mgmnt.service.LoginService;

@Controller
public class LoginController {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	LoginService loginService;
	
	@Autowired
	EventService eventService;

	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		if((binder.getClass().getName()).equalsIgnoreCase("USER"))
			binder.addValidators(validator);
	}


	//@RequestMapping(value = "/login", method = RequestMethod.GET)
	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		User userDetails = null;
		String userLoggedAlready = (String) request.getSession().getAttribute("userlogged");
		if (!StringUtils.isEmpty(userLoggedAlready) && "Y".equalsIgnoreCase(userLoggedAlready)) {
			userDetails = (User) request.getSession().getAttribute("user");
		}else {
			userDetails = new User();
		}
		ModelAndView mv = new ModelAndView("LoginPage");
		mv.addObject("loginform", userDetails);
		return mv;

	}

	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest request, @ModelAttribute("loginform") Login loginObj, BindingResult bindingResult,
			ModelAndView modelMap) {

		String result = "";
		

		if (StringUtils.isEmpty(loginObj.getUsername()) || StringUtils.isEmpty(loginObj.getPassword())) {
			bindingResult.addError(new ObjectError("Login", "Kindly provide Usename/Password"));
			result = "redirect:/login";
		}else {
			User userDetails = null;
			try {
				loginObj.setPassword(EncryptionUtility.encrypt(loginObj.getPassword()));

				String userLoggedAlready = (String) request.getSession().getAttribute("userlogged");
				if (!StringUtils.isEmpty(userLoggedAlready) && "Y".equalsIgnoreCase(userLoggedAlready)) {
					userDetails = (User) request.getSession().getAttribute("user");

				} else {
					userDetails = loginService.getUserDetails(loginObj);
					request.getSession().setAttribute("user", userDetails);
					request.getSession().setAttribute("userlogged", "Y");
					
				}
				result = "redirect:/home";
				
			} catch (EventServiceException e) {
				bindingResult.rejectValue(null, e.getErrorCode(),
						"Error while processing the request");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		modelMap.setViewName(result);

		return modelMap;
	}

	@PostMapping("/signup")
	public String registerUser(@ModelAttribute("userform")  @Validated User user,  BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			logger.info("Returning empSave.jsp page");
			return "registration";
		}

		try {
			user.setPassword(EncryptionUtility.encrypt(user.getPassword()));
			user.setCreationDate(new Date());
			user.setAuthorities(new ArrayList<AuthorityName>(Arrays.asList(AuthorityName.ROLE_ADMIN)) );
		} catch (EventServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loginService.registerUser(user);
		} catch (EventMgmntDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
