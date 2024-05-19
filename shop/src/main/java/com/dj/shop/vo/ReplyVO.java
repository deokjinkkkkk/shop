package com.dj.shop.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
    private Long rno; //(댓글 번호)
    private Long bno; // 댓글 종류 (게시판)

    private String reply; // 댓글 내용
    private String userName;// 작성자
    private Date repDate; // 댓글 작성 시간
    private Date repUpDate; // 댓글 수정 시간;
    private int repDel; //댓글 삭제 여부;
}
