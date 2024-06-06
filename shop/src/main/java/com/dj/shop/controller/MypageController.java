package com.dj.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dj.shop.service.AddressService;
import com.dj.shop.service.UserService;
import com.dj.shop.service.wishilistService;
import com.dj.shop.vo.AddressVO;
import com.dj.shop.vo.UserVO;
import com.dj.shop.vo.WishlistVO;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/myPage")
public class MypageController {
	private static final org.slf4j.Logger logger =org.slf4j.LoggerFactory.getLogger(ProdcutController.class);
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	wishilistService wishilist;

	@GetMapping("/myAddress")
	public String myAddress(UserVO vo, HttpServletRequest request,Model model) {
		/*세션 값을 통해서 유저 번호 가지고오기*/
		String id = (String) request.getSession().getAttribute("email");
		vo.setEmail(id);
		vo =userService.userSelect(id);
		try {
			model.addAttribute("addressInfo", addressService.getAddressInfo(vo.getUserNumber()));
		} catch (NullPointerException e) {
			// 로그인 하지 않고 주소지목록 접근시 동작
		}
		
		return "pages/user/myAddress";
	}
	
	@PostMapping("/address/create")
	public String addressCreate(AddressVO avo,UserVO uvo,HttpServletRequest request) {
		/*세션 값을 통해서 유저 번호 가지고오기*/
		String id = (String) request.getSession().getAttribute("email");
		uvo.setEmail(id);
		uvo =userService.userSelect(id);
		int usernumber = uvo.getUserNumber();
		
		avo.setUserNumber(usernumber);
		addressService.createAddress(avo);
		return "redirect:/myPage/myAddress";
	}
	
	@PostMapping("/address/delete")
	public String deleteCreate(AddressVO avo) {

		addressService.deleteAddress(avo);
		return "redirect:/myPage/myAddress";
	}
	
	@GetMapping("/wishlist")
	public String wishlist() {
		return "pages/user/wishlist";
	}
	@GetMapping("/checkWishiList")
	@ResponseBody
	public int wishiListCheck(@RequestParam("productNum") int productNum,WishlistVO vo,HttpServletRequest request) {
		
		String id = (String) request.getSession().getAttribute("email");
		UserVO uvo = userService.userSelect(id);
		vo.setUserNumber(uvo.getUserNumber());
		vo.setProductNum(productNum);
		int result = wishilist.checkWishiList(vo);
		logger.info(productNum + " " +vo.getUserNumber() + " " + result);
		return result;
	}
	@PostMapping("/changeWishiList")
	@ResponseBody
	public int changeWishiList(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
		int productNum = Integer.parseInt(payload.get("productNum").toString());
    	String action = (String) payload.get("action");
		
		String id = (String) request.getSession().getAttribute("email");
		UserVO uvo = userService.userSelect(id);
		WishlistVO vo = new WishlistVO();
		vo.setUserNumber(uvo.getUserNumber());
		vo.setProductNum(productNum);
		logger.info(productNum+"");
		logger.info(vo.getUserNumber() + "");
		int result = 0;
		if ("add".equals(action)) {
			result = wishilist.addWishiList(vo);
		} else if ("remove".equals(action)) {
			result = wishilist.removeWishiList(vo);
		}
		
		return result;
	}
	
	
}
