package com.dj.shop.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dj.shop.mapper.CartMapper;
import com.dj.shop.mapper.OrderMapper;
import com.dj.shop.mapper.ProductMapper;
import com.dj.shop.mapper.UserMapper;
import com.dj.shop.vo.CartVO;
import com.dj.shop.vo.OrderVO;
import com.dj.shop.vo.OrdersItemVO;
import com.dj.shop.vo.OrdersVO;
import com.dj.shop.vo.ProductVO;
import com.dj.shop.vo.UserVO;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderMapper order;
	
	@Autowired
	CartMapper cart;
	
	@Autowired
	ProductMapper product;
	
	@Autowired
	UserMapper user;
	
	@Override
	public List<OrderVO> getProductInfo(List<OrderVO> orders) {
		List<OrderVO> result =new ArrayList<OrderVO>();
		
		for(OrderVO ord : orders) {
			
			OrderVO productInfo = order.getProductInfo(ord.getProductNum());
			
			productInfo.setProCnt(ord.getProCnt());
			result.add(productInfo);
		}
		
		return result;
	}


	@Override
	@Transactional
	public void order(OrdersVO ord) {
		
		
	//주문자 정보 가져오기
		//회원정보
		UserVO userNumber = user.getUserInfo(ord.getUserNumber());
		//상품정보
		List<OrdersItemVO> orders =new ArrayList<OrdersItemVO>();
		for(OrdersItemVO ori : ord.getOrders()) {
			
			OrdersItemVO orderItem = order.getOrderInfo(ori.getProductNum());
			//재고 수량
			orderItem.setOrderCnt(ori.getOrderCnt());
			//리스트에 상품 정보 넣기
			orderItem.setOrderPrice(ori.getOrderPrice());
			orders.add(orderItem);
		}
		//
		ord.setOrders(orders);
	//DB에 주문 정보 넣기
		//주문 번호 날짜로 해서 만들기
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
		
		String orderNum = userNumber.getUserNumber() + format.format(date);
		//DB에 넣기
		ord.setOrderNum(orderNum);
		for(OrdersItemVO ori : ord.getOrders()) {
			ori.setOrderNum(orderNum);
			order.insertOrderItem(ori);
		}
		//상품 재고 주문한 만큼 삭제
		for(OrdersItemVO ori : ord.getOrders()) {
			ProductVO pro = product.getData(ori.getProductNum());
			pro.setProCnt(pro.getProCnt() - ori.getOrderCnt());
			
			order.deleteCnt(pro);
		}
		//장바구니에 있는 상품 삭제
		for(OrdersItemVO ori : ord.getOrders()) {
			CartVO vo = new CartVO();
			vo.setUserNumber(ord.getUserNumber());
			vo.setProductNum(ori.getProductNum());
			
			cart.cartOrderDelete(vo);
		}
		order.insertOrder(ord);
	}


	@Override
	public List<OrdersVO> getOrdersInfo(OrdersVO vo) {
		
		return  getOrdersInfo(vo);
	}

}
