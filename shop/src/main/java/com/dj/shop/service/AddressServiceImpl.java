package com.dj.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.AddressMapper;
import com.dj.shop.vo.AddressVO;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	AddressMapper address;
	
	@Override
	public int createAddress(AddressVO vo) {
		
		return address.createAddress(vo);
	}

	@Override
	public AddressVO getAddressInfo(int usernumber) {
		
		return address.getAddressInfo(usernumber);
	}

	@Override
	public int updateAddress(AddressVO vo) {
		
		return address.updateAddress(vo);
	}

	@Override
	public int deleteAddress(AddressVO vo) {
		
		return address.deleteAddress(vo);
	}

}
