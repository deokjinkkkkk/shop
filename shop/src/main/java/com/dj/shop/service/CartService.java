package com.dj.shop.service;

import java.util.List;

import com.dj.shop.vo.CartVO;

public interface CartService {
	// 장바구니 추가
    public int addCart(CartVO vo);
    // 장바구니 상품 여부 확인
    public CartVO checkCart(CartVO vo);
    // 장바구니 목록
    public List<CartVO> cartList(int userNumber);
    //장바구니 삭제
    public int cartDelete(int cartNum);
    //장바구니 수정
    public int cartUpdate(CartVO vo);
  
}
