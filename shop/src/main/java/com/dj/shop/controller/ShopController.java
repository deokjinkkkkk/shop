package com.dj.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class ShopController {
	@GetMapping("/")
	public String main() {
		return "pages/index";
	}
	@GetMapping("/login")
	public String login() {
		return "pages/login";
	}
	
	@GetMapping("/review")
	public String review() {
		return "pages/review";
	}
	

}
