package com.dj.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.shop.service.UserService;
import com.dj.shop.vo.UserVO;

@Controller
public class UserController {
	@Autowired
	UserService userservice;
	
	@PostMapping("/signUp")
	public String signUp(UserVO vo) {
		userservice.saveUser(vo);
		return "redirect:/login";
	}
	
	@RequestMapping("/nameChk")
	@ResponseBody
	public String idchk(@RequestParam("name") String name,boolean n) { //아이디가 존재하면 실패 해야하기 때문에 true 인 경우 suceess 하게 구현
		n = userservice.idCheck(name); //boolean 타입으로 전환
		if(n) {
			return "success";
		}else {
			return "fail";
		}
	}
}
