package com.dj.shop.service;

import java.util.List;

import com.dj.shop.vo.OrderVO;

public interface OrderService {
	/*주문 상품 정보*/
	
	public List<OrderVO> getProductInfo(List<OrderVO> orders);
	/*주문 상품 등록*/
	public int insertOrder(OrderVO vo);
	/*주문 취소*/
	public int deleteOrder(int OrderNum);
	/*주문 수정*/
	public int updateOrder(OrderVO vo);
	
}
