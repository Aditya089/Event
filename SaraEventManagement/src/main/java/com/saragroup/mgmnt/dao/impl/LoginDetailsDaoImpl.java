package com.saragroup.mgmnt.dao.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.saragroup.mgmnt.dao.LoginDetailsDao;
import com.saragroup.mgmnt.exception.EventMgmntDaoException;
import com.saragroup.mgmnt.helper.EncryptionUtility;
import com.saragroup.mgmnt.model.Login;
import com.saragroup.mgmnt.model.User;

@Repository("loginDao")
public class LoginDetailsDaoImpl implements LoginDetailsDao {
	private final Log LOGGER = LogFactory.getLog(LoginDetailsDaoImpl.class);

	@Autowired
	MongoOperations mongoTemplate;

	public User authUser(Login user) throws EventMgmntDaoException {
		LOGGER.info("Fetching User details." + user);

		if (mongoTemplate == null) {
			LOGGER.fatal("Mongo DB Template Not configured");
		}

		User userDtls = null;
		try {
			Query query = new Query();
			userDtls = mongoTemplate.findOne(query.addCriteria(Criteria.where("username").is(user.getUsername())),
					User.class);
			if(userDtls != null && user.getPassword().equals(EncryptionUtility.decrypt(userDtls.getPassword()))) {
				return userDtls;
			}
		} catch (Exception e) {
			LOGGER.fatal("Exception occured while fetching the User", e);
			throw new EventMgmntDaoException(e);
		}

		LOGGER.info("Successfuly retrieved the requested User." + userDtls);

		return userDtls;
	}

	public void registerUserDetails(User user) throws EventMgmntDaoException {
		LOGGER.info("Commiting User. " + user);

		if (mongoTemplate == null) {
			LOGGER.fatal("Mongo DB Template Not configured");
		}

		if (user != null) {
			try {
				mongoTemplate.save(user);
				LOGGER.info("User Commited Successfully. Generated Id " + user.getUserId());
			} catch (Exception e) {
				LOGGER.fatal("Exception occured while Registering the User", e);
				throw new EventMgmntDaoException(e);
			}
		}

	}

	public boolean userExists(String userName) throws EventMgmntDaoException {
		LOGGER.info("Verifying User Existance : " + userName);

		if (mongoTemplate == null) {
			LOGGER.fatal("Mongo DB Template Not configured");
		}

		int val = 0;
		if (!StringUtils.isEmpty(userName)) {
			try {

				Query query = new Query();
				query.addCriteria(Criteria.where("username").is(userName));

				long userTest1 = mongoTemplate.count(query, User.class);
				val = (int) userTest1;
				LOGGER.info("Matching UserName records count: " + val);
			} catch (Exception e) {
				LOGGER.fatal("Exception occured while Registering the User", e);
				throw new EventMgmntDaoException(e);
			}

			LOGGER.info("No. of Matching UserName records Found: " + val);
		}

		LOGGER.info("UserName Passed is Empty.");

		return (val > 0) ? true : false;
	}

	@Override
	public void updateLastLoggedById(String userId) {
		User temp = mongoTemplate.findById(userId, User.class);
		temp.setLastLogin(new Date());
		mongoTemplate.save(temp);
	}

}
