package com.dj.shop.service;

import java.util.List;

import com.dj.shop.vo.OrderVO;
import com.dj.shop.vo.OrdersVO;

public interface OrderService {
	/*주문 상품 정보*/
	public List<OrderVO> getProductInfo(List<OrderVO> orders);
	
	/*주문 결제 */
	public void order(OrdersVO vo);

	/*주문 목록 */
	public List<OrdersVO> getOrdersInfo(int userNumber);
	/*주문 취소 */


}
