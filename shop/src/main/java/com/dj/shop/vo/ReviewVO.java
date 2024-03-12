package com.dj.shop.vo;

import java.util.Date;

import lombok.Data;

@Data	
public class ReviewVO {
	private int revNum;
	private int userNumber;
	private String revTitle;
	private String revContent;
	private String revReply;
	private Date revDate;
	private String revRep;
	
}
