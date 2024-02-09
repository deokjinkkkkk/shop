package com.dj.shop.mapper;

import com.dj.shop.vo.UserVO;


public interface UserMapper {
	UserVO userSelect(String vo); //로그인
	int saveUser(UserVO vo); //회원가입
	boolean idCheck(String id); //아이디 중복체크
			
}
