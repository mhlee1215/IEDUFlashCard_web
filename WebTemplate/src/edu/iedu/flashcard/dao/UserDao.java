package edu.iedu.flashcard.dao;

import java.util.List;

import edu.iedu.flashcard.dao.domain.RS_User;


public interface UserDao {
	public RS_User readUser(RS_User user);
	public void createUser(RS_User user);
	public void deleteUser(RS_User user);
	public void updateUser(RS_User user);
	public List<RS_User> findAll();
}
