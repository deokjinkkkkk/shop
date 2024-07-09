package com.dj.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.CartMapper;
import com.dj.shop.mapper.ProductMapper;
import com.dj.shop.service.inf.CartService;
import com.dj.shop.vo.CartVO;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cart;
    
    @Autowired
    ProductMapper product;
    @Override
    public int addCart(CartVO vo) {
    	int cntCheck = product.productCheck(vo.getProductNum());
    	//장바구니 확인 여부
       if(cart.checkCart(vo) != null) {
    	   return 2; 
       }
       
       if(cntCheck < 1 || cntCheck < vo.getCartCnt()) {
    	   return 4; //재고 확인 
       }
       
        try {
        	//장바구니 추가
			return cart.addCart(vo);
		} catch (Exception e) {
			//장바구니 추가 실패
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
