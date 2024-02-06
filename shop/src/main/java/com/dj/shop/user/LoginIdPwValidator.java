package com.dj.shop.user;

import org.apache.catalina.realm.JNDIRealm.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	public UserDetails loadUserByUsername(String insertedId) throws UsernameNotFoundException {
		// 사용자가 입력한 id가 인자로 들어옴 -> 유저id로 DTO꺼내오기
		UserDTO mdto = userMapper.getMemberById(insertedId);
	        
	        if (mdto == null) {
	            return null; // ID 혹은 PW가 잘못되었습니다.	            
	        }
	        
	        String passwd = mdto.getMem_passwd();
	        String roles = mdto.getMem_role();

	        return User.builder()
	                .username(insertedId)
	                .password(passwd)
	                .roles(roles)
	                .build();
	}

}
