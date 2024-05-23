package com.dj.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@GetMapping("/list")
	public String reviewForm() {

		return "pages/review/reviewlist";
	}
	
}
