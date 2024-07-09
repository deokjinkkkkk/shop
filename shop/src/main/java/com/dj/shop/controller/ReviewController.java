package com.dj.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.dj.shop.service.inf.ReviewService;
import com.dj.shop.service.inf.UserService;
import com.dj.shop.vo.ReviewVO;
import com.dj.shop.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



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
		vo.setUserNumber(userNumber);

		return review.reviewAdd(vo);
	}
	
	@GetMapping("/list")
	public List<ReviewVO> getMethodName(ReviewVO rvo) {
		List<ReviewVO> result = review.reviewList(rvo);
		logger.info("로그"+result);
		return result;
	}
	
}
