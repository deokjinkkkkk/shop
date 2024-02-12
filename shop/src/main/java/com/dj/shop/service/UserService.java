package com.dj.shop.service;

import com.dj.shop.vo.UserVO;

public interface UserService {
	UserVO userSelect(String vo); //로그인
	int saveUser(UserVO vo); //회원가입
	boolean idCheck(String id); //아이디 중복체크
	boolean emailChk(String email);
}
