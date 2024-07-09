package com.dj.shop.service.inf;

import java.util.List;

import com.dj.shop.vo.ReviewVO;

public interface ReviewService {
    /*리뷰 작성 */
    public int reviewAdd(ReviewVO vo);

    /*리뷰 불러오기*/
    public List<ReviewVO> reviewList(ReviewVO rvo);
}
