package com.dj.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.CartMapper;
import com.dj.shop.vo.CartVO;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cart;

    @Override
    public int addCart(CartVO vo) {
    	System.out.println(vo.getProductNum());
       if(cart.checkCart(vo) != null) {
    	   return 2;
       }
       
        try {
			return cart.addCart(vo);
		} catch (Exception e) {
			
			return 0;
		}
		
    }

	@Override
	public CartVO checkCart(CartVO vo) {
		
		return cart.checkCart(vo);
	}

	@Override
	public List<CartVO> cartList(int userNumber) {
		
		return cart.cartList(userNumber);
	}

	@Override
	public int cartDelete(int cartNum) {
		
		return cart.cartDelete(cartNum);
	}

	@Override
	public int cartUpdate(CartVO vo) {
		
		return cart.cartUpdate(vo);
	}
}
