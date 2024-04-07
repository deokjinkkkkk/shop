package com.dj.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dj.shop.service.AddressService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.AddressVO;
import com.dj.shop.vo.UserVO;

@Controller
@RequestMapping("/myPage")
public class MypageController {
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	@GetMapping("/myAddress")
	public String myAddress(UserVO vo, HttpServletRequest request,Model model) {
		/*세션 값을 통해서 유저 번호 가지고오기*/
		String id = (String) request.getSession().getAttribute("email");
		vo.setEmail(id);
		vo =userService.userSelect(id);
		
		model.addAttribute("addressInfo", addressService.getAddressInfo(vo.getUserNumber()));
		return "pages/myAddress";
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
		
		return "pages/wishlist";
	}
}
