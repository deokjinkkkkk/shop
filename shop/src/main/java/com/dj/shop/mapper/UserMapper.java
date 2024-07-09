package com.dj.shop.mapper;

import com.dj.shop.vo.UserVO;


public interface UserMapper {
	//로그인(email을 사용해서 유저정보 가져오기)
	UserVO userSelect(String email); 
	//회원가입
	int saveUser(UserVO vo); 
	//아이디 중복체크
	boolean idCheck(String id); 
	//이메일 확인
	boolean emailChk(String email); 
	//유저 번호를 통해 유저정보 가져오기
	UserVO getUserInfo(int userNumber);
			
}
