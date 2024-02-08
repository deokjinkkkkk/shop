package com.dj.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.dj.shop.service.UserService;
import com.dj.shop.vo.UserVO;

@Controller
public class UserController {
	@Autowired
	UserService userservice;
	
	@PostMapping
	public String signUp(UserVO vo) {
		
		return "redirect:/login";
	}
}
