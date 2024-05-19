package com.dj.shop.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dj.shop.service.AdminService;
import com.dj.shop.vo.OrdersVO;
import com.dj.shop.vo.Pagination;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Log4j2
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService admin;
    
    @GetMapping("/list")
    public String orderList(@RequestParam(value = "page",required = false) Integer page,
                            OrdersVO vo){
        Pagination pagination = new Pagination();
	    //페이지의 값이 있는경우
	    if(page != null) {
	    	pagination.setPage(page);
	    }
	    log.info("1");
	    //상품 총 개수 
	    int totalCount = admin.getOrderTotal(vo);
	    //상품 개수를 페이징 처리하기 위해 넣어주기
	    pagination.setTotalRecord(totalCount);
	    //보여질 페이지 vo에 담아주기
		vo.setFirst(pagination.getFirst());
		vo.setLast(pagination.getLast());
		
        List<OrdersVO> list = admin.getOrderList(vo);
        return "pages/admin/orderList";
    }
}
