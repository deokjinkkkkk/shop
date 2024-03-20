package com.dj.shop.service;

import com.dj.shop.vo.CartVO;

public interface CartService {
    public int addCart(CartVO vo);
    public CartVO checkCart(CartVO vo);
}
