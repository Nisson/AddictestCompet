package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	
	

	@Override
	public List<User> showAllUsers() {
		return (List<User>) userRepository.findAll(); 
	} 
	
	@Override
	public User addUser(User u) {
		return userRepository.save(u); 
	} 
	
	@Override
	public void deleteUser(String id) {
		
		userRepository.deleteById(Long.parseLong(id)); 
	} 
	
	@Override
	public User updateUser(User u) {
		
		return userRepository.save(u); 
	} 
	 
	@Override
	public User retrieveUser(String id) {
		// Optional retrun type - Java 8 (susceptible de retourner des valeurs « vides » et pas null) 
		return userRepository.findById(Long.parseLong(id)).orElse(null); 
	} 
	
}
