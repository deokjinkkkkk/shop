package com.dj.shop.service.inf;

import com.dj.shop.vo.AddressVO;

public interface AddressService {
	//주소지 등록
	public int createAddress(AddressVO vo);
	//주소지 불러오기
	public AddressVO getAddressInfo(int usernumber);
	//주소지 수정
	public int updateAddress(AddressVO vo);
	//주소지 삭제
	public int deleteAddress(AddressVO vo);
}
