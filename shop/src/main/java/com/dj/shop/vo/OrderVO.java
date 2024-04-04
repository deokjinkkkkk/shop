package com.dj.shop.vo;

import java.util.Date;

import lombok.Data;
@Data
public class OrderVO {
	private int orderNum; 
	private int userNumber;
	private int orderPrice;
	private Date orderDate;
	private String orderState;
	private String orderWay; // 주문 방법
	private String orderAdd1;
	private String orderAdd2;
	private String orderTel;
	private String orderName;
	private String orderReq; //요청사항
	private int orderCnt;
	private String orderProname; //상품 이름
	private String orderImg;
	private String orderZip; //우편번호
	
	private int productNum;
	
}
