package com.dj.shop.vo;

import lombok.Data;

@Data
public class Criteria {
    private int pageNum; // 현재 페이지 번호
    private int amount; // 한 페이지당 항목 수
    private int startNum; // 시작 위치

    public Criteria() {
        this(1, 10); // 기본값으로 1페이지와 한 페이지당 10개의 항목을 설정
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        calculateStartNum();
    }

    // 시작 위치 계산
    private void calculateStartNum() {
        this.startNum = (pageNum - 1) * amount;
    }

    // 시작 위치 설정
    public void setStartNum(int startNum) {
        this.startNum = startNum;
        // pageNum 재계산
        if (amount != 0) {
            pageNum = (startNum / amount) + 1;
        }
    }
}

