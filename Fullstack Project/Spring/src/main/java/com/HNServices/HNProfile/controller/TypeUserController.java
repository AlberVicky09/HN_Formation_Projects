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

import com.HNServices.HNProfile.entity.TypeUser;
import com.HNServices.HNProfile.service.TypeUserService;

@RestController
@CrossOrigin
@RequestMapping("/typeUsers")
public class TypeUserController {

	@Autowired
	private TypeUserService typeUserService;
	
	@GetMapping("")
	public List<TypeUser> getTypeUser() {
		return typeUserService.get();
	}
	
	@GetMapping("/{typeUserId}")	
	public TypeUser findTypeUserById(@PathVariable int typeUserId) {
		//Get userType
		return typeUserService.get(typeUserId);

	}
	
	@PostMapping("")
	public boolean addTypeUser(@RequestBody TypeUser typeUser) {
		return typeUserService.save(typeUser);
	}
	
	@PutMapping("/{typeUserId}")
	public boolean updateTypeUser(@PathVariable int typeUserId, @RequestBody TypeUser typeUser) {
		return typeUserService.update(typeUserId, typeUser);
	}
	
	@DeleteMapping("/{typeUserId}")
	public boolean removeTypeUser(@PathVariable int typeUserId) {
		return typeUserService.delete(typeUserId);
	}
}
