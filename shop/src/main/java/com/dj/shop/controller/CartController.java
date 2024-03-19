package com.dj.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.dj.shop.service.CartService;

@Controller
public class CartController {
	@Autowired
	CartService cart;
	
	@PostMapping("/cartInsert")
	public String cartInsert() {
		return null;
		
	}
}
