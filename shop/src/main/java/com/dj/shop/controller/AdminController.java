package com.dj.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dj.shop.service.AdminService;
import com.dj.shop.service.OrderService;
import com.dj.shop.service.ProductService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.OrdersVO;
import com.dj.shop.vo.Pagination;

import groovyjarjarpicocli.CommandLine.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
	    pagination.setPageSize(5); //한 페이지에 보여질 페이지 갯수	
	    pagination.setPageUnit(9);//한 페이지에 출력할 레코드 건수
	    
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
