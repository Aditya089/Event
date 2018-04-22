package com.saragroup.mgmnt.dao;

import com.saragroup.mgmnt.exception.EventMgmntDaoException;
import com.saragroup.mgmnt.model.Login;
import com.saragroup.mgmnt.model.User;

public interface LoginDetailsDao {
	public User authUser(Login user) throws EventMgmntDaoException;

	public boolean userExists(String string) throws EventMgmntDaoException;

	public void registerUserDetails(User user) throws EventMgmntDaoException;

	public void updateLastLoggedById(String userId);



}
