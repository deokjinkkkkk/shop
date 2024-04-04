package com.dj.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dj.shop.vo.OrderVO;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	
	@GetMapping("/form")
	public String checkoutForm() {
		return "/pages/checkout";
	}
	
	
}
