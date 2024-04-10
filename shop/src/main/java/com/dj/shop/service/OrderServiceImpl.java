package com.dj.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.OrderMapper;
import com.dj.shop.vo.OrderVO;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderMapper order ;
	
	
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
	public int insertOrder(OrderVO vo) {
		// TODO Auto-generated method stub
		return order.insertOrder(vo);
	}


	@Override
	public int deleteOrder(int OrderNum) {
		// TODO Auto-generated method stub
		return order.deleteOrder(OrderNum);
	}


	@Override
	public int updateOrder(OrderVO vo) {
		// TODO Auto-generated method stub
		return order.updateOrder(vo);
	}

}
