package com.dj.shop.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVO implements UserDetails {

	private int userNumber;
	private String name;
	private String email;
	private String userPwd;
	private String tel;
	private String gender;
	private String suspen;
	private String signout;
	private String roles;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return roles;
			}
		});
		return authorities;
	}
	@Override
	public String getPassword() {
		
		return userPwd;
	}
	@Override
	public String getUsername() {
		
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		 
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
