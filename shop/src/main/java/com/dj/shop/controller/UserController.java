package com.dj.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dj.shop.service.EmailService;
import com.dj.shop.service.inf.UserService;
import com.dj.shop.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserService userservice;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "pages/user/login";
	}
	
	@PostMapping("/signUp")
	public String signUp(@RequestBody UserVO vo, @RequestParam("domain") String domain) {
		vo.setUserPwd(bCryptPasswordEncoder.encode(vo.getPassword())); 
		vo.setEmail(vo.getUsername() + '@' + domain);
		userservice.saveUser(vo);
		return "redirect:/login";
	}
	
}
