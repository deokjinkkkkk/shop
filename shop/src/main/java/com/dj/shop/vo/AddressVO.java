package com.dj.shop.vo;

import lombok.Data;

@Data
public class AddressVO {
	private int addNum;
	private int userNumber;
	private String addName;
	private String addZip; //우편번호
	private String addAddr;
	private String addAddr2;
	private String addTel;
	private String addReq; // 요청사항

}
