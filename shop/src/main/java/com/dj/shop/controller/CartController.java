package com.dj.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public int cartInsert(@RequestParam("cnt") int cnt,
			CartVO vo,UserVO uvo, HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email"); //세션 이메일값 가져오기
		uvo = user.userSelect(email); //유저 번호 가져오기
		
		if(uvo == null) {
			return 3; //로그인 하지 않았을때 처리
		}
		
		vo.setUserNumber(uvo.getUserNumber()); //로그인한 유저 번호 카트DB에 담아주기 
		vo.setCartCnt(cnt); //카트 수량추가
		int result = cart.addCart(vo); //장바구니에 추가
		return result ;
		
	}
	//AJAX 를 사용해서 홈페이지 상단 장바구니 정보 가져오기
	@GetMapping("/headlist")
	@ResponseBody
	public List<CartVO> headcartList(@RequestParam("email")String email,
									UserVO vo, CartVO cvo) {
		vo = user.userSelect(email);
		
		List<CartVO> result = null;
		// 상품 카트 에 값이 없을경우 처리
		try {
			result = cart.cartList(vo.getUserNumber());
		} catch (NullPointerException e) {
			result = null; 
		}
		
		
		return result;
		
	}
	
	@GetMapping("/cartlist") //장바구니 정보 가져오기, 결제 페이지 넘어가기전 
	public String CartList(CartVO vo,@RequestParam("email")String email,
							Model model,UserVO uvo) {
		uvo.setEmail(email);
		
		uvo = user.userSelect(email);
		
		if(uvo == null) {
			return "redirect:/";
		} //상품 로그인 없이 장바구니 페이지 넘어갈려고 할때 페이지로 리턴 
		
		List<CartVO> result = cart.cartList(uvo.getUserNumber());
		
		if(result.isEmpty()) {
			return "pages/cart/nullCartList";
		} //장바구니 추가한 데이터가 없을때 이동하는 페이지
		
		model.addAttribute("cartList", result); //model에 리스트 담아두기
		return "pages/cart/cartlist" ;	
	}

	//장바구니 물건 삭제
	@PostMapping("/cartDelete")
	public String cartListDelete(CartVO vo,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");// 이메일 세션에서 가져오기
		cart.cartDelete(vo.getCartNum()); 
		return "redirect:/cart/cartlist?email=" + email;	
	}
	
	//비동기(AJAX)형태로 물건 삭제
	@PostMapping("/cartDeleteAjax")
	@ResponseBody
	public int cartListDeleteAjax(@RequestParam("cartNum") int cartNum) {
		
		int result =cart.cartDelete(cartNum);
		return result;	
	}
	//장바구니 수량 변경
	@PostMapping("/cartUpdate")
	public String cartListUpdate(CartVO vo,HttpServletRequest request) {
		String email = (String) request.getSession().getAttribute("email");
		cart.cartUpdate(vo);
		return "redirect:/cart/cartlist?email=" + email;
	
	}
}
