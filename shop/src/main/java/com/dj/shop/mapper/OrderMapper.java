package com.dj.shop.mapper;

import com.dj.shop.vo.OrderVO;
import com.dj.shop.vo.OrdersItemVO;
import com.dj.shop.vo.OrdersVO;
import com.dj.shop.vo.ProductVO;
import com.dj.shop.vo.ProductVO;

public interface OrderMapper {
	/*상품 정보*/
	public OrderVO getProductInfo(int productNum);
	/*주문 상품 정보*/
	public OrdersItemVO getOrderInfo(int productNum);	
	/*주문 상품 등록*/
	public int insertOrder(OrdersVO vo);
	/*주문 아이템 등록*/
	public int insertOrderItem(OrdersItemVO vo);
	/*주문 취소 (미구현)*/
	public int deleteOrder(int OrderNum);
	/*주문 재고 변경*/
	public int deleteCnt(ProductVO pvo);
}
