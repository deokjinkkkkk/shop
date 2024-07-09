package com.dj.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.UserMapper;
import com.dj.shop.vo.UserVO;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserMapper usermapper;
	
	// 시큐리티 session(내부 Authentication(네부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = usermapper.userSelect(username);
		if(user != null) {
			return user;
		}
		return null;
	}

}
