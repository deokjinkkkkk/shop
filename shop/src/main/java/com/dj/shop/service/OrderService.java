package com.dj.shop.service;

import java.util.List;

import com.dj.shop.vo.OrderVO;
import com.dj.shop.vo.OrdersVO;

public interface OrderService {
	/*주문 상품 정보*/
	public List<OrderVO> getProductInfo(List<OrderVO> orders);
	
	public void order(OrdersVO vo);
	
}
