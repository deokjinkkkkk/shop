package com.dj.shop.mapper;

import java.util.List;

import com.dj.shop.vo.OrderVO;

public interface OrderMapper {
	/*주문 상품 정보*/
	
	public OrderVO getProductInfo(int orderNum);
	/*주문 상품 등록*/
	public int insertOrder(OrderVO vo);
	/*주문 취소*/
	public int deleteOrder(int OrderNum);
	/*주문 수정*/
	public int updateOrder(OrderVO vo);
}
