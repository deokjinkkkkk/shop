package com.dj.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import com.dj.shop.config.jwt.JwtAuthenticationFilter;
import com.dj.shop.config.jwt.JwtAuthorizationFilter;
import com.dj.shop.mapper.UserMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CorsConfig corsConfig;

	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();// POST방식 허용
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 세션을 사용하지 않음
				.and().addFilter(corsConfig.corsFilter()).formLogin().disable().httpBasic().disable()
				.addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager
				.addFilter(new JwtAuthorizationFilter(authenticationManager(), userMapper)).authorizeRequests()
				// .antMatchers(
				// "/home","/","/product/**","/assets/**","/user/login").permitAll() // 이 URI는
				// 누구든 접근가능
				// .antMatchers("/admin/**").hasRole("ADMIN")// ADMIN role만 접근 가능
				// .access("hasRole('USER') or hasRole('ADMIN')");
				.anyRequest().permitAll();
		
	}

	// 인증 예외 추가
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/asstes/**");
		web.httpFirewall(defaultHttpFirewall());
	}

	// url 더블 슬래시 허용 (이미지 불러오기 위해)
	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}
}
