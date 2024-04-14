package com.dj.shop.vo;

import lombok.Data;
@Data
public class OrderVO {
	/*주문 한 상품의 데이터*/
	//뷰로 받아올 값
	private int productNum;
	private int proCnt;
	//DB로 꺼내올 값
	private String proName; //상품 이름
	private int proPrice;

	private String proImg1;
	
}
