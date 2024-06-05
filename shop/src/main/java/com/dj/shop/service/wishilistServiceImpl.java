package com.dj.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.WishiListMapper;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWishiList'");
    }

    @Override
    public int addWishiList(WishlistVO wvo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addWishiList'");
    }

    @Override
    public int removeWishiList(WishlistVO wvo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeWishiList'");
    }

}
