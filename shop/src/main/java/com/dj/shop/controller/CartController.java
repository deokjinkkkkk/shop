package com.dj.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.shop.service.CartService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.CartVO;
import com.dj.shop.vo.UserVO;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cart;
	@Autowired
	UserService user;
	
	@PostMapping("/add")
	@ResponseBody
	public int cartInsert(CartVO vo,UserVO uvo, HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		uvo = user.userSelect(email);
		
		if(uvo == null) {
			return 3;
		}
		vo.setUserNumber(uvo.getUserNumber());
		int result = cart.addCart(vo);
		return result ;
		
	}
}
