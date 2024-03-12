package com.dj.shop.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ProductVO{
	private int productNum;
	private int categoryNum; //카테고리 구분
	private String proName; //상품이름
	private String proImg1; //상품이미지 1
	private String proImg2;
	private String proImg3;
	private String proContent; //상품내용
	private int proPrice; 
	private Date proDate; //상품등록일
	private int proCnt; //재고
	private String proDel; //상품 삭제여부
	

	
}
