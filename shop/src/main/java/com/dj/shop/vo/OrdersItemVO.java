package com.dj.shop.vo;

import lombok.Data;

@Data
public class OrdersItemVO {
	private int orderDnum;
	private int orderNum;
	private int productNum;
	private int orderPrice;
	private int orderCnt;
}
