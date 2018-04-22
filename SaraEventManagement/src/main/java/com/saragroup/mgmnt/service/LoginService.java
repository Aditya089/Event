package com.saragroup.mgmnt.service;

import com.saragroup.mgmnt.exception.EventMgmntDaoException;
import com.saragroup.mgmnt.model.Login;
import com.saragroup.mgmnt.model.User;

public interface LoginService {
	public User getUserDetails(Login user) throws Exception;

	public void registerUser(User user) throws EventMgmntDaoException;
}
