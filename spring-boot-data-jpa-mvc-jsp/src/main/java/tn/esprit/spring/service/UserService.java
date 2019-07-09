package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.User;

public interface UserService { 
	 
	List<User> showAllUsers(); 

	User addUser(User u);

	void deleteUser(String id);

	User updateUser(User u);

	User retrieveUser(String id);
	 
} 
 