package com.dj.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.UserMapper;
import com.dj.shop.vo.UserVO;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper user;
	
	@Override
	public UserVO userSelect(String vo) {
		
		return user.userSelect(vo);
	}

	@Override
	public int saveUser(UserVO vo) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setUserPwd(passwordEncoder.encode(vo.getUserPwd()));
		return user.saveUser(vo);
	}

	@Override
	public boolean idCheck(String id) {
		
		return user.idCheck(id);
	}

	@Override
	public boolean emailChk(String email) {
		
		return user.emailChk(email);
	}

	@Override
	public UserVO getUserInfo(int userNumber) {
		return user.getUserInfo(userNumber);
	}

}
