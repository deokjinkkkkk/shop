package com.dj.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.UserMapper;
import com.dj.shop.vo.UserVO;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper user;
	
	@Override
	public UserVO userSelect(String vo) {
		// TODO Auto-generated method stub
		return user.userSelect(vo);
	}

}
