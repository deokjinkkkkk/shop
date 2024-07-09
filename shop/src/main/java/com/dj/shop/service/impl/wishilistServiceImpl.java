package com.dj.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.WishiListMapper;
import com.dj.shop.service.inf.wishilistService;
import com.dj.shop.vo.WishlistVO;
@Service
public class wishilistServiceImpl implements wishilistService{
    
    @Autowired
    WishiListMapper wish;

    @Override
    public int checkWishiList(WishlistVO wvo) {
        int result = wish.checkWishiList(wvo);
        return result;
    }

    @Override
    public List<WishlistVO> getWishiList(WishlistVO wvo) {
        return wish.getWishiList(wvo);
    }

    @Override
    public int addWishiList(WishlistVO wvo) {
        return wish.addWishiList(wvo);
    }

    @Override
    public int removeWishiList(WishlistVO wvo) {
        return wish.removeWishiList(wvo);
    }

}
