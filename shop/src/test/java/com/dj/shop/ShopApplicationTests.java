package com.dj.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dj.shop.service.ProductService;
import com.dj.shop.service.UserService;
import com.dj.shop.vo.ProductVO;
import com.dj.shop.vo.UserVO;

@SpringBootTest
class ShopApplicationTests {
	@Autowired
	UserService userservice;
	
	@Autowired
	ProductService pro;
	
	@Test
	public void mailChk() {
		String email = "antan3957";
		boolean result = userservice.emailChk(email);
		UserVO user = userservice.userSelect(email);
		ProductVO vo = new ProductVO();
		vo.setCategoryNum(1);
		pro.count(vo);
		System.out.println(pro.count(vo));
		System.out.println(user.getAuthorities());
		if (result) {
			System.out.println("success");
			
		} else {
			System.out.println("fail");
			
		}
		
	}

}
