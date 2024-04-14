package com.dj.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dj.shop.mapper.CartMapper;
import com.dj.shop.mapper.OrderMapper;
import com.dj.shop.mapper.ProductMapper;
import com.dj.shop.vo.OrderVO;
import com.dj.shop.vo.OrdersItemVO;
import com.dj.shop.vo.OrdersVO;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderMapper order;
	
	@Autowired
	CartMapper cart;
	
	@Autowired
	ProductMapper product;
	
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
		for(OrdersItemVO ori = ord.getOrders()) {
			
		}
	}

}
