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
	private String orderZip;
	private String orderAdd1;
	private String orderAdd2;
	private String orderTel;
	private String orderName;
	private String orderReq;
	private int orderCnt;
	private String orderProname;
	private String orderImg;
	
}
