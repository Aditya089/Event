package com.saragroup.mgmnt.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saragroup.mgmnt.dao.LoginDetailsDao;
import com.saragroup.mgmnt.exception.AuthenticationException;
import com.saragroup.mgmnt.exception.EventMgmntDaoException;
import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.helper.EncryptionUtility;
import com.saragroup.mgmnt.model.Login;
import com.saragroup.mgmnt.model.User;
import com.saragroup.mgmnt.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	LoginDetailsDao loginDao;

	public User getUserDetails(Login user) throws EventServiceException {
		LOGGER.info("Initializing User Authentication " + user);

		User userLogged = null;

		try {
			userLogged = authenticateUser(user);
			updateLastLoggedIn(userLogged.getDocumentId());
		} catch (Exception e) {
			LOGGER.fatal("Exception Occured while Retrieving dat from DAO Layer");
			throw new AuthenticationException(e);
		}

		return userLogged;
	}

	private void updateLastLoggedIn(String userId) {
		LOGGER.info("Updating last login time for userId" + userId);
		loginDao.updateLastLoggedById(userId);

	}

	private User authenticateUser(Login user) throws EventServiceException, EventMgmntDaoException {
		User userDtls = loginDao.authUser(user);

		if (userDtls == null) {
			LOGGER.fatal("Username does not exist for reqeusted user" + user.getUsername());
			throw new AuthenticationException("USER_NOT_EXIST");
		}

		if (user.getPassword().equals(userDtls.getPassword())) {
			LOGGER.info("User successfully authenticated.");
			userDtls.setRePassword(userDtls.getPassword());
			return userDtls;
		} else {
			LOGGER.fatal("Username & Password not matching");
			throw new AuthenticationException("INCORRECT_CREDENTIALS");
		}

	}

	@Override
	public void registerUser(User user) throws EventServiceException {
		try {
			loginDao.registerUserDetails(user);
		} catch (EventMgmntDaoException e) {
			throw new EventServiceException(e);
		}

	}

}
