package com.saragroup.mgmnt.dao.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.saragroup.mgmnt.dao.LoginDetailsDao;
import com.saragroup.mgmnt.exception.EventMgmntDaoException;
import com.saragroup.mgmnt.model.Login;
import com.saragroup.mgmnt.model.User;

@Repository("loginDao")
public class LoginDetailsDaoImpl implements LoginDetailsDao {
	private final Logger LOGGER = Logger.getLogger(LoginDetailsDaoImpl.class);

	@Autowired
	MongoOperations mongoTemplate;

	public User authUser(Login user) throws EventMgmntDaoException {
		LOGGER.info("Fetching User details.For Username:" + user.getUsername());

		if (mongoTemplate == null) {
			LOGGER.fatal("Mongo DB Template Not configured");
		}

		User userDtls = null;
		try {
			Query query = new Query();
			userDtls = mongoTemplate.findOne(query.addCriteria(Criteria.where("username").is(user.getUsername())),
					User.class);
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
				LOGGER.info("User Commited Successfully. Generated Id " + user.getDocumentId());
			} catch (Exception e) {
				LOGGER.fatal("Exception occured while Registering the User", e);
				throw new EventMgmntDaoException(e);
			}
		}

	}

	@Override
	public void updateLastLoggedById(String userId) {
		User temp = mongoTemplate.findById(userId, User.class);
		temp.setLastLogin(new Date());
		temp.setRePassword("ENCRYPTED");
		mongoTemplate.save(temp);
	}

}
