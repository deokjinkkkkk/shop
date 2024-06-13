package com.dj.shop.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersVO{
	/*주문 정보*/
	private String orderNum;
	private int userNumber;
	private Date orderDate;
	private String orderState;
	private String orderZip; //우편번호
	private String orderAdd1;
	private String orderAdd2;
	private String tel;
	private String name;
	private String orderReq; // 요청사항
	private String proImg1; // 상품이미지
	
	private List<OrdersItemVO> orders;
	
	/*페이징 처리 */
	Integer first;
	Integer last;
}
