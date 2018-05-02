package com.saragroup.mgmnt.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.saragroup.mgmnt.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User userObj = (User) obj;
		if (!userObj.getPassword().equals(userObj.getRePassword()))
			errors.rejectValue("rePassword", "Passwords mismatch");
	}
}
