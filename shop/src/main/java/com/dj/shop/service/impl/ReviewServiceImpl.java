package com.dj.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.controller.ProdcutController;
import com.dj.shop.mapper.ReviewMapper;
import com.dj.shop.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService{
private static final org.slf4j.Logger logger =org.slf4j.LoggerFactory.getLogger(ProdcutController.class);

	@Autowired
	ReviewMapper review;

	@Override
	public int reviewAdd(ReviewVO vo) {
		Integer check = review.checkReview(vo); //상품 리뷰 작성여부 확인

		int result;
		if(check != null){
			return result = 3;
		}
		result = review.reviewAdd(vo);

		return result;
	}

	@Override
	public List<ReviewVO> reviewList(ReviewVO vo) {
		int count = review.reviewCount(vo);	 //리뷰 작성수 
		vo.setTotalRecord(count); // 페이징 처리를 위해 리뷰 개수
		return review.reviewList(vo);
	}
}
