package com.dj.shop.mapper;

import java.util.List;

import com.dj.shop.vo.OrdersVO;

public interface AdminMapper {
    /*주문 현황 (관리자)*/
	public List<OrdersVO> getOrderList(OrdersVO vo);
	/*주문 개수 */
	public int getOrderTotal(OrdersVO vo);
	/*주문 현황 변경 */
}
