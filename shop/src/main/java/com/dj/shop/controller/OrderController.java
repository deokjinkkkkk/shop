package com.dj.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dj.shop.service.AddressService;
import com.dj.shop.service.OrderService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.AddressVO;
import com.dj.shop.vo.OrderPageVO;
import com.dj.shop.vo.OrdersVO;
import com.dj.shop.vo.UserVO;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderservice;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/{email}")
	public String orderPageForm(@PathVariable("email") String email,
			OrderPageVO ovo,Model model,UserVO vo) {
		vo = userService.userSelect(email);
		int userNumber = vo.getUserNumber();
		AddressVO address = addressService.getAddressInfo(userNumber);
		model.addAttribute("orderList",orderservice.getProductInfo(ovo.getOrders()));
		model.addAttribute("userInfo", vo);
		if(address == null) {
			address = addressService.getAddressInfo(2);
		}
		model.addAttribute("addressInfo",address);
		return "pages/order/order";
	}
	
	@PostMapping("/plus")
	public String orderPlus(OrdersVO ovo ,UserVO uvo,HttpServletRequest request) {
		
		orderservice.order(ovo);
		uvo.setUserNumber(ovo.getUserNumber());
		
		/*주문 고객 충전금액 부분 세션 최신화*/
		/*
		HttpSession session = request.getSession();
		
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return "redirect:/";
	}
}
