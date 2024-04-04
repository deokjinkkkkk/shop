package com.dj.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dj.shop.service.OrderService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.OrderPageVO;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderservice;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{email}")
	public String orderPageForm(@PathVariable("email") String email,
			OrderPageVO ovo,Model model) {
		
		model.addAttribute("orderList",orderservice.getProductInfo(ovo.getOrders()));
		model.addAttribute("userInfo",userService.getUserInfo(email));

		return "pages/order";
	}
}
