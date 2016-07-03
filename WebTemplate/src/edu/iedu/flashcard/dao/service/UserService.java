package edu.iedu.flashcard.dao.service;

import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.mail.EmailException;

import edu.iedu.flashcard.dao.domain.User;



public interface UserService {
	public int readUser(User user) throws Exception;
	public User readUserData(User user) throws Exception;
	public int createUser(User user) throws Exception;
	public void updateUser(User user);
	public List<User> findAll();
	public int deleteUser(User user);
}
