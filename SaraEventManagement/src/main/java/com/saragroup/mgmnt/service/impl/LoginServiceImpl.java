package com.saragroup.mgmnt.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saragroup.mgmnt.dao.LoginDetailsDao;
import com.saragroup.mgmnt.exception.EventMgmntDaoException;
import com.saragroup.mgmnt.exception.EventServiceException;
import com.saragroup.mgmnt.model.Login;
import com.saragroup.mgmnt.model.User;
import com.saragroup.mgmnt.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Autowired
	LoginDetailsDao loginDao;

	public User getUserDetails(Login user) throws EventServiceException {
		LOGGER.info("Initializing User Authentication " + user);

		User userLogged = null;
		
		try {
			if (isUserAvailable(user.getUsername())) {
				userLogged = authenticateUser(user);
				updateLastLoggedIn(userLogged.getUserId());
			}
		} catch (EventMgmntDaoException e) {
			LOGGER.fatal("Exception Occured while Retrieving dat from DAO Layer");
			throw new EventServiceException(e);
		}

		return userLogged;
	}

	private void updateLastLoggedIn(String userId) {
		loginDao.updateLastLoggedById(userId);
		
	}

	private User authenticateUser(Login user) throws EventServiceException, EventMgmntDaoException {
		User userDtls = loginDao.authUser(user);
		if (userDtls == null) {
			LOGGER.fatal("Username & Password not matching");
			throw new EventServiceException("INCORRECT_CREDENTIALS");
		}
		return userDtls;
	}

	private boolean isUserAvailable(String userName) throws EventServiceException, EventMgmntDaoException {
		boolean userExists = loginDao.userExists(userName);

		if (!userExists) {
			throw new EventServiceException("USER_NOT_FOUND");
		}

		return true;
	}

	@Override
	public void registerUser(User user) throws EventMgmntDaoException {
		loginDao.registerUserDetails(user);
	}

}
