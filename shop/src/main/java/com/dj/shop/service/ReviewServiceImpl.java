package com.dj.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl {
	@Autowired
	ReviewMapper review;
}
