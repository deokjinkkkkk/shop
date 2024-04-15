package com.dj.shop.service;

import com.dj.shop.vo.UserVO;

public interface UserService {
	public UserVO userSelect(String vo); //로그인
	public int saveUser(UserVO vo); //회원가입
	public boolean idCheck(String id); //아이디 중복체크
	public boolean emailChk(String email);
	
	public UserVO getUserInfo(int userNumber);
}
