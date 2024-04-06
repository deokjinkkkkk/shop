package com.dj.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.shop.service.EmailService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userservice;
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/signUp")
	public String signUp(UserVO vo, @RequestParam("domain") String domain) {
		vo.setEmail(vo.getEmail() + '@' + domain);
		userservice.saveUser(vo);
		return "redirect:/login";
	}

	@PostMapping("/emailChk")
	@ResponseBody
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
		@ResponseBody
		public String mailCheck(@RequestParam String email,HttpSession session) throws Exception {
			String code = emailService.sendSimpleMessage(email);
			session.setAttribute("emailCode", code);
			return code;
		}
}
