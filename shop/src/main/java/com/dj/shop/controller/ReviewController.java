package com.dj.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.dj.shop.service.ReviewService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.ReviewVO;
import com.dj.shop.vo.UserVO;


@RestController
@RequestMapping("/review")
public class ReviewController {
	private static final org.slf4j.Logger logger =org.slf4j.LoggerFactory.getLogger(ProdcutController.class);

	@Autowired
	private ReviewService review;

	@Autowired
	private UserService user;
	@PostMapping("/add")
	public int reviewAdd(ReviewVO vo,UserVO uvo ,HttpServletRequest request) {
		String id = (String)request.getSession().getAttribute("email");
		if (id == null) {
			return 2;
		}
		uvo = user.userSelect(id);
		int userNumber = uvo.getUserNumber();
		
		vo.setProductNum(userNumber);

		logger.info("댓글 등록 완료");
		return review.reviewAdd(vo);
	}
	
}
