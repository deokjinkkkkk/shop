package com.dj.shop.mapper;

import com.dj.shop.vo.OrderVO;

public interface OrderMapper {
	/*주문 상품 정보*/
	
	public OrderVO getProductInfo(int productNum);
}
