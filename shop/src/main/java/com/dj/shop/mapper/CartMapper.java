package com.dj.shop.mapper;

import com.dj.shop.vo.CartVO;

public interface CartMapper {
    public int addCart(CartVO vo) throws Exception;
    public CartVO checkCart(CartVO vo);
}
