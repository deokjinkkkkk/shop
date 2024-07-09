package com.dj.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.AdminMapper;
import com.dj.shop.service.inf.AdminService;
import com.dj.shop.vo.OrdersVO;
import com.dj.shop.vo.Pagination;
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminMapper admin;

    @Override
    public List<OrdersVO> getOrderList(OrdersVO vo) {

        Pagination pagination = new Pagination();
	    //페이지의 값이 있는경우
	    pagination.setPageSize(5); //한 페이지에 보여질 페이지 갯수	
	    pagination.setPageUnit(9);//한 페이지에 출력할 레코드 건수
	    
        return admin.getOrderList(vo);
    }
    @Override
    public int getOrderTotal(OrdersVO vo) {
        throw new UnsupportedOperationException("Unimplemented method 'getOrderTotal'");
    }

}
