package com.dj.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dj.shop.service.EmailService;
import com.dj.shop.service.inf.UserService;

@RestController
public class UserRestController {
	@Autowired
	UserService userservice;
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/emailChk")
	public String mailChk(@RequestParam("email") String email, boolean n) {
		n = userservice.emailChk(email);
		if (n) {
			return "success";
		} else {
			return "fail";
		}
		
	}
	
	//회원가입 이메일 비밀번호 발급
	@PostMapping("/mailConfirm")
	public String mailCheck(@RequestParam String email,HttpSession session) throws Exception {
		String code = emailService.sendSimpleMessage(email);
		session.setAttribute("emailCode", code);
		return code;
	}
}
