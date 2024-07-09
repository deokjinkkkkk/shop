package com.dj.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.UserMapper;
import com.dj.shop.service.inf.UserService;
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
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //BCrypt 암호화 생성자 
		vo.setUserPwd(passwordEncoder.encode(vo.getUserPwd())); //암호화해서 DB에 저장
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
