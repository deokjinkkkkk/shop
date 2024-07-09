package com.dj.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dj.shop.service.inf.CartService;
import com.dj.shop.vo.CartVO;

@SpringBootTest
public class cartTest {
	@Autowired
	private CartService cart;
	
	
	@Test
	public void cartCheck() {
		CartVO vo = new CartVO();
		int userNumber = 1;
		int productNum = 7;
		int cartCnt = 1;
		vo.setCartCnt(cartCnt);
		vo.setUserNumber(userNumber);
		vo.setProductNum(productNum);
		
		System.out.println(cart.addCart(vo));
	}
}
