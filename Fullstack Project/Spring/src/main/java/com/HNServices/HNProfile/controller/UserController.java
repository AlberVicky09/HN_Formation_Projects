package com.HNServices.HNProfile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HNServices.HNProfile.entity.Users;
import com.HNServices.HNProfile.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public List<Users> findAll() {
		return userService.get();
	}
	
	@GetMapping("/{userId}")
	public Users findUserById(@PathVariable int userId) {
		return userService.get(userId);
	}
	
	@PutMapping("/{userId}")
	public boolean updateUser(@PathVariable int userId, @RequestBody Users user) {
		return userService.update(userId, user);
	}
	
	@PostMapping("")
	public boolean addUser(@RequestBody Users user) {
		return userService.save(user);
	}
	
	@DeleteMapping("/{userId}")
	public boolean removeUser(@PathVariable int userId) {
		return userService.delete(userId);
	}
}
