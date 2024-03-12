package com.dj.shop.vo;

import lombok.Data;

@Data
public class PageVO {
	//페이징 기능 
	private int startPage;
	private int endPage;
	private boolean prev, next; //이전 페이지,다음페이지 존재 유무
	private int total;
	private Criteria cri; //
	
	public PageVO(Criteria cri, int total) {
		this.endPage = (int) (Math.ceil(cri.getPageNum()/ 10.0) * 10);
		this.startPage = this.endPage - 9;
		
		int realEnd = (int) (Math.ceil(total * 1.0) / cri.getAmount()); // ceil 올림
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
	}
}
