package com.example.devicemap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.devicemap.entity.User;
import com.example.devicemap.service.UserService;

@RestController
public class UserController {

	@Autowired
	public UserService userService;
	
	
	@GetMapping("/findAllRegisteredUsers")
	public ResponseEntity<List<User>> getAlluser(){
		try {
		      List<User> allUsers = userService.getAllUser();
		
		      if (allUsers.isEmpty()) {
		    	  
		       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		     }
		       return new ResponseEntity<>(allUsers,HttpStatus.FOUND);
		
		}catch(Exception e) {
			   //status.setStatusDesc("Empty List");
			   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		     }
		}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
		try {
		      User getUserById = userService.getuserByUserId(userId);
		      return new ResponseEntity<>(getUserById,HttpStatus.FOUND);
		}catch(Exception e) {
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
	    try {
		      User addUser = userService.addUser(user);
		      return new ResponseEntity<>(addUser,HttpStatus.CREATED);
		
	    }catch (Exception e) {
    	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable String userId,@RequestBody User user) {
	    try {
		      userService.updateUser(userId, user);
		      return new ResponseEntity<>(user,HttpStatus.OK);
	    }catch(Exception e) {
		      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	    }
    }
	
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<User> deleteUserById(@PathVariable("userId") String userId) {
		try {
		      userService.deleteUser(userId);
		      return new ResponseEntity<>(HttpStatus.OK);
	    }catch(Exception e) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	}
}
