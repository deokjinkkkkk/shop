package com.dj.shop.vo;

import java.util.Date;

import lombok.Data;

@Data	
public class ReviewVO {
	private int revNum; //리뷰 번호
	private int userNumber; //유저 번호
	private int productNum; //상품 번호
	private int revStar; // 리뷰 별점
	private String revContent; //리뷰 내용
	private String revReply; //답변내용
	private Date revDate; //문의 날짜
	
}
