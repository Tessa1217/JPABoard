package com.vuejpa.demo.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vuejpa.demo.user.entity.UserRequestDTO;
import com.vuejpa.demo.user.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	
	@RequestMapping(value="/users/signUp.do")
	@ResponseBody
	public String userSignUp(@RequestBody UserRequestDTO request) {
		userService.insertUser(request);
		return "success";
	}

}
