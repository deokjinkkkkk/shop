package com.dj.shop.mapper;

import java.util.List;

import com.dj.shop.vo.WishlistVO;

public interface WishiListMapper {
    /*위시리스트 체크 */
    public int checkWishiList(WishlistVO wvo);
    /*위시리스트 불러오기 */
    public List<WishlistVO> getWishiList(WishlistVO wvo);
    /*위시리스트 추가 */
    public int addWishiList(WishlistVO wvo);
    /*위시리스트 삭제 */
    public int removeWishiList(WishlistVO wvo);
}
