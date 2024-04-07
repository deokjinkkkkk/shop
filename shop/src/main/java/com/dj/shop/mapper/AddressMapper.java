package com.dj.shop.mapper;

import java.util.List;

import com.dj.shop.vo.AddressVO;

public interface AddressMapper {
	//주소지 등록
	public int createAddress(AddressVO vo);
	//주소지 불러오기
	public List<AddressVO> getAddressInfo(int usernumber);
	//주소지 수정
	public int updateAddress(AddressVO vo);
	//주소지 삭제
	public int deleteAddress(AddressVO vo);
}
