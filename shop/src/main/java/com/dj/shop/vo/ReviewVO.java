package com.dj.shop.vo;

import java.util.Date;

import lombok.Data;

@Data	
public class ReviewVO {
	private int revNum;
	private int userNumber;
	private int productNum;
	private String revTitle;
	private String revContent;
	private String revReply; //답변내용
	private Date revDate; //문의 날짜
	private String revRep; //답변 유무
	
}
