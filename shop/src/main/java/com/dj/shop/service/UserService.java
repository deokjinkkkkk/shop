package com.dj.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.UserMapper;
import com.dj.shop.vo.UserVO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
	
	@Autowired
	private final UserMapper usermapper;
	
	// 시큐리티 session(내부 Authentication(네부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = usermapper.userSelect(username);
		System.out.println("PrincipalDetailsService : 진입" + user.getUsername());
		return user;
	}

}
