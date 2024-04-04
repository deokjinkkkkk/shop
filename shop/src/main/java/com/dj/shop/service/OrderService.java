package com.dj.shop.service;

import java.util.List;

import com.dj.shop.vo.OrderVO;

public interface OrderService {
	/*주문 정보*/
	List<OrderVO> getProductInfo(List<OrderVO> orders);
}
