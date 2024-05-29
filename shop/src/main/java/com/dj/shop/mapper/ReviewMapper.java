package com.dj.shop.mapper;

import java.util.List;

import com.dj.shop.vo.ReviewVO;

public interface ReviewMapper {
    /*리뷰 등록 */
    public int reviewAdd(ReviewVO rvo);
    /*리뷰 작성 여부 */
    public Integer checkReview(ReviewVO rvo);
    /*리뷰 불러오기*/
    public List<ReviewVO> reviewList(ReviewVO rvo);
    /*리뷰 작성 개수 */
    public int reviewCount(ReviewVO rvo);
}
