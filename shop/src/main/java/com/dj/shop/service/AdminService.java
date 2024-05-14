package com.dj.shop.service;

import java.util.List;

import com.dj.shop.vo.OrdersVO;

public interface AdminService {
    /*주문 현황 */
	public List<OrdersVO> getOrderList(OrdersVO vo);
	/*주문 개수 */
	public int getOrderTotal(OrdersVO vo);
	/*주문 현황 변경 */
}
