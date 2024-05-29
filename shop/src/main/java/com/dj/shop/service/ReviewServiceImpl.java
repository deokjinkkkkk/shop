package com.dj.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.ReviewMapper;
import com.dj.shop.vo.Pagination;
import com.dj.shop.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewMapper review;

	@Override
	public int reviewAdd(ReviewVO vo) {
		Integer check = review.checkReview(vo);
		int result;
		if(check != null){
			return result = 3;
		}
		result = review.reviewAdd(vo);
		return result;
	}

	@Override
	public List<ReviewVO> reviewList(ReviewVO vo) {
		int count = review.reviewCount(vo);	
		vo.setTotalRecord(count);
		return review.reviewList(vo);
	}
}
