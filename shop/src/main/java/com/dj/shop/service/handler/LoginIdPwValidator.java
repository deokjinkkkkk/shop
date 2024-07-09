package com.dj.shop.service.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dj.shop.mapper.UserMapper;
import com.dj.shop.vo.UserVO;

@Service
public class LoginIdPwValidator implements UserDetailsService {
	@Autowired
    private UserMapper userMapper;

	// DB의 pw(암호화된)와 유저가 입력한 pw를 암호화하여 자동으로 비교
	
	  @Bean 
	  public PasswordEncoder passwordEncoder() { 
		  return new BCryptPasswordEncoder(); 
	  }
	

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
			UserVO vo = userMapper.userSelect(userId);
	        
	        if (vo == null) {
	            throw new UsernameNotFoundException("유저 없음"); 	            
	        }
	        String passwd = vo.getUserPwd();
	        String roles =  vo.getRoles();

	        return User.builder()
	                .username(userId)
	                .password(passwd)
	                .roles(roles)
	                .build();
	}

}
