package edu.iedu.flashcard.dao.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import edu.iedu.flashcard.dao.UserDao;
import edu.iedu.flashcard.dao.domain.User;
import edu.iedu.flashcard.dao.util.Crypto;



@Service
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserDao		userDao;
	
	boolean isEncrypt = true;

	public List<User> findAll() {
		return userDao.findAll();
	}

	public int createUser(User user) throws Exception {
		
		
		//Encrypt
        if(isEncrypt)
        	 user.setPassword(Crypto.encrypt(user.getPassword()));       
		User paramUser = new User();
		paramUser.setEmail(user.getEmail());
		User foundUser = readUserData(paramUser);
		logger.debug("create User");
		logger.debug("==[S]============================");
		
		if(foundUser != null){
			logger.debug("User is already registered. Cancel Register.");
			logger.debug("==[E]============================");
			return User.STATUS_ALREADY_REGISTEREDED;
		}
		else{
			logger.debug("User doesn't find. Go Register.");
			userDao.createUser(user);
			return User.STATUS_SUCCESS_REGISTER;
		}
		
		
	}

	public int readUser(User user) throws Exception {
		
		if(isEncrypt)
		    user.setPassword(Crypto.encrypt(user.getPassword()));
        //
		User readed = userDao.readUser(user);
		
		if(readed == null){
			return User.STATUS_NOT_FOUNDED;
		}else{
			return User.STATUS_FOUNDED;
		}
	}
	
	public User readUserData(User user) throws Exception {
		return userDao.readUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	public int deleteUser(String email) {
		User paramUser = new User();
		paramUser.setEmail(email);
		userDao.deleteUser(paramUser);
		
		return User.STATUS_SUCCESS_DELETED;
		
	}
}
