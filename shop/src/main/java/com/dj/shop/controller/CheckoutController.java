package com.dj.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	
	@GetMapping("/form")
	public String checkoutForm() {
		return "/pages/checkout";
	}
}
