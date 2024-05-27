package com.dj.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.dj.shop.service.ReviewService;
import com.dj.shop.vo.ReviewVO;


@RestController
@RequestMapping("/review")
public class ReviewController {
	private static final org.slf4j.Logger logger =org.slf4j.LoggerFactory.getLogger(ProdcutController.class);
	@Autowired
	private ReviewService review;

	@PostMapping("/add")
	public void reviewAdd(ReviewVO vo) {
		logger.info("댓글 등록 완료");
		review.reviewAdd(vo);
	}
	
}
