package com.dj.shop.service;

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
       
        return cart.addCart(vo);
    }
}
