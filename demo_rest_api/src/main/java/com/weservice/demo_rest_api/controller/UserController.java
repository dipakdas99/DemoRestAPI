package com.weservice.demo_rest_api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.weservice.demo_rest_api.bean.UserBean;
import com.weservice.demo_rest_api.exception.UserNotFoundException;
import com.weservice.demo_rest_api.service.UserDaoService;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/user")
	public List<UserBean> retrieveAllUser() {
		//find all users
		return userDaoService.findAll();
	}

	@GetMapping("/user/{id}")
	public UserBean retieveOne(@PathVariable int id) {
		//find individual user
		UserBean user = userDaoService.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id: "+id);
		return user;
	}

	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserBean user) {
		// Create users
		UserBean savedUser = userDaoService.save(user);
		// returning correct response status as (201) for successful creation
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		//delete individual user
		UserBean user = userDaoService.deleteById(id);
		if (user == null)
			throw new UserNotFoundException("id: "+id);
	}

}
