package com.dj.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;

import com.dj.shop.filter.JwtFilter;
import com.dj.shop.service.handler.LoginFailHandler;
import com.dj.shop.service.handler.LoginIdPwValidator;
import com.dj.shop.service.handler.LoginSuccessHandler;

import lombok.RequiredArgsConstructor;
@Configuration 
@EnableWebSecurity 
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final CorsFilter corsFilter;
	
	@Autowired
    LoginIdPwValidator loginIdPwValidator;
	
	 protected void configure(HttpSecurity http) throws Exception {
		 	http.addFilterBefore(new JwtFilter(), SecurityContextPersistenceFilter.class);
	        http.csrf().disable();// POST방식 허용
	        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 세션을 사용하지 않음
	        		.and()
	        		.addFilter(corsFilter) 
	                .authorizeRequests()
	                	.antMatchers( "/home","/","/product/**","/assets/**","/user/login").permitAll() // 이 URI는 누구든 접근가능
	                	.antMatchers("/admin/**").hasRole("ADMIN")// ADMIN role만 접근 가능
	                	.anyRequest().access("hasRole('USER') or hasRole('ADMIN')")
	                .and()
	                    .formLogin().disable()
	                    .httpBasic().disable(); // 
	    }
	
    //인증 예외 추가
    @Override
    public void configure(WebSecurity web) throws Exception {
    	 web.ignoring().antMatchers("/resources/**");
    	 web.httpFirewall(defaultHttpFirewall());
    }
	
	//url 더블 슬래시 허용 (이미지 불러오기 위해)
    @Bean 
	public HttpFirewall defaultHttpFirewall() {
	    return new DefaultHttpFirewall();
	}
    //입력한 ID/PW가 DB와 일치하는지 확인
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginIdPwValidator);
    }

    //로그인 성공 핸들러
    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }
    
    //로그인 실패 핸들러
    @Bean
    public LoginFailHandler loginFailHandler(){
        return new LoginFailHandler();
    }
    
}

