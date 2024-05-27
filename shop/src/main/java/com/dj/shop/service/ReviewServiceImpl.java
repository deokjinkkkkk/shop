package com.dj.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.ReviewMapper;
import com.dj.shop.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewMapper review;

	@Override
	public int reviewAdd(ReviewVO vo) {
		int result = review.reviewAdd(vo);
		
		return result;
	}
}
