package com.dj.shop.mapper;

import com.dj.shop.vo.AddressVO;

public interface AddressMapper {
	//주소지 등록
	public int createAddress(AddressVO vo);
	//주소지 리스트 불러오기
	public AddressVO getAddressInfo(int usernumber);
	//주소지 수정
	public int updateAddress(AddressVO vo);
	//주소지 삭제
	public int deleteAddress(AddressVO vo);
}
