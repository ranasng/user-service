package com.user.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.entity.ContactEntity;
import com.user.entity.UserDataEntity;
import com.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RestTemplate restTemp;
	
@GetMapping("/{userId}")
	public UserDataEntity getUserData(@PathVariable("userId") Long userId) {
	UserDataEntity user=userService.getUser(userId);
	//List contact=restTemp.getForObject("http://localhost:9002/usercontact/contact/"+user.getUserId(), List.class);
	List contact=restTemp.getForObject("http://contact-service/usercontact/contact/"+user.getUserId(), List.class);
	user.setContact(contact);
	return user;
	}
}
