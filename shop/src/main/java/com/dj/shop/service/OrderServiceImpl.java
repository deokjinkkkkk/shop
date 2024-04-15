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
			System.out.println(productInfo);
			productInfo.setProCnt(ord.getProCnt());
			result.add(productInfo);
		}
		
		return result;
	}


	@Override
	@Transactional
	public void order(OrdersVO ord) {
	
		List<OrdersItemVO> orders =new ArrayList<OrdersItemVO>();
		UserVO userNumber = user.getUserInfo(ord.getUserNumber());
		for(OrdersItemVO ori : ord.getOrders()) {
			OrdersItemVO orderItem = order.getOrderInfo(ori.getProductNum());
			orderItem.setOrderCnt(ori.getOrderCnt());
			
			orders.add(orderItem);
		}
		
		ord.setOrders(orders);
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
		
		String orderNum = userNumber.getUserNumber() + format.format(date);
		ord.setOrderNum(orderNum);
		for(OrdersItemVO ori : ord.getOrders()) {
			ori.setOrderNum(orderNum);
			order.insertOrderItem(ori);
		}
		
		for(OrdersItemVO ori : ord.getOrders()) {
			ProductVO pro = product.getAllSangpums(ori.getProductNum());
			pro.setProCnt(pro.getProCnt() - ori.getOrderCnt());
			
			order.deleteOrder(pro);
		}
		
		for(OrdersItemVO ori : ord.getOrders()) {
			CartVO vo = new CartVO();
			vo.setUserNumber(ord.getUserNumber());
			vo.setProductNum(ori.getProductNum());
			
			cart.cartOrderDelete(vo);
		}
	}

}
