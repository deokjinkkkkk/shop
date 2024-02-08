package com.dj.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dj.shop.service.ProductService;

@Controller
public class ProdcutController {
	@Autowired
	ProductService prodcutervice;
}
