package com.saragroup.mgmnt.validators;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.saragroup.mgmnt.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		ValidationUtils.rejectIfEmpty(err, "username", "user.name.empty");
		ValidationUtils.rejectIfEmpty(err, "email", "user.email.empty");
		ValidationUtils.rejectIfEmpty(err, "password", "user.pass.empty");
		ValidationUtils.rejectIfEmpty(err, "firstName", "user.fname.empty");
		ValidationUtils.rejectIfEmpty(err, "lastName", "user.lname.empty");
		

		User user = (User) obj;
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if (!(pattern.matcher(user.getEmail()).matches())) {
			err.rejectValue("email", "user.email.invalid");
		}
	}

}
