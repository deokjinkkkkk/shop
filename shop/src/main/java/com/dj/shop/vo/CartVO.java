package com.dj.shop.vo;

import lombok.Data;

@Data
public class CartVO {
    private int cartNum;
    private int userNumber;
    private int productNum;
    private int cartCnt;
    
    /*join해서 가져올 데이터 */
    private String productName;//상품명
    private int proPrice;//상품가격
    
    private String proImg1; //상품이미지 1
    private int price ;//가격
    
    private int rn; //rownum 사용
    
    //상품 총가격
    
}
