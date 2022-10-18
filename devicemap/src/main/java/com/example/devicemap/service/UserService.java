package com.example.devicemap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devicemap.entity.User;
import com.example.devicemap.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public List<User> getAllUser(){
		List<User> users=new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return users;
	}
	
	public User getuserByUserId(String userId) {
		User getUser = userRepo.findById(userId).get();
		return getUser;
	}
	
	public User addUser(User user) {
		return userRepo.save(user);
		
	}
	public User updateUser(String userId,User user) {

		User user1 = userRepo.findById(userId).get();
		 user1.setUserId(user1.getUserId());
		 user1.setUsername(user1.getUsername());
         user1.setCreatedDate(user1.getCreatedDate());
         
		return userRepo.save(user);
		
	}
	

	public void deleteUser(String userId) {
		
		userRepo.deleteById(userId);
		
	}

	
}
