package com.dj.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.shop.service.EmailService;
import com.dj.shop.service.inf.UserService;
import com.dj.shop.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userservice;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/login")
	public String login() {
		return "pages/user/login";
	}
	
	@PostMapping("/signUp")
	public String signUp(UserVO vo, @RequestParam("domain") String domain) {
		vo.setEmail(vo.getEmail() + '@' + domain);
		userservice.saveUser(vo);
		return "redirect:/login";
	}

	
}
