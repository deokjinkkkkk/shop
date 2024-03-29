package com.dj.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.shop.service.CartService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.CartVO;
import com.dj.shop.vo.UserVO;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cart;
	@Autowired
	UserService user;
	
	@PostMapping("/add")
	@ResponseBody
	public int cartInsert(CartVO vo,UserVO uvo, HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		uvo = user.userSelect(email);
		
		if(uvo == null) {
			return 3;
		}
		vo.setUserNumber(uvo.getUserNumber());
		int result = cart.addCart(vo);
		return result ;
		
	}
	@GetMapping("/headlist")
	@ResponseBody
	public List<CartVO> headcartList(@RequestParam("email")String email,
									UserVO vo) {
		vo = user.userSelect(email);
		List<CartVO> result = cart.cartList(vo.getUserNumber());
		
		return result;
		
	}
	
	@GetMapping("/cartlist")
	public String CartList(CartVO vo,@RequestParam("email")String email,
							Model model,UserVO uvo) {
		uvo.setEmail(email);
		uvo = user.userSelect(email);
		
		model.addAttribute("cartList",cart.cartList(uvo.getUserNumber()));
		
		return "pages/cartlist" ;	
	}
	@PostMapping("cartDelete")
	public int cartListDelete(@RequestParam("email")String email,
				UserVO uvo ,CartVO vo) {
		uvo = user.userSelect(email);
		int result = cart.cartDelete(vo.getCartNum());
		return result;
	
	}
	@PostMapping("cartUpdate")
	public int cartListUpdate(@RequestParam("email")String email,
				UserVO uvo ,CartVO vo) {
		uvo = user.userSelect(email);
		int result = cart.cartUpdate(vo);
		return result;
	
	}
}
