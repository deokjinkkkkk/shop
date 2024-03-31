package com.dj.shop.controller;

import javax.mail.Address;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dj.shop.service.AddressService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.UserVO;

@Controller
@RequestMapping("/myPage")
public class MypageController {
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	@GetMapping("/myAddress")
	public String myAddress(UserVO vo, HttpServletRequest request,Model model) {
		String id = (String) request.getSession().getAttribute("email");
		vo.setEmail(id);
		userService.userSelect(id);
		model.addAttribute("userList", vo);
		return "pages/myAddress";
	}
}
