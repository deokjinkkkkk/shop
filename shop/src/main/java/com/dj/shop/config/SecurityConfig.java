package com.dj.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dj.shop.user.LoginFailHandler;
import com.dj.shop.user.LoginIdPwValidator;
import com.dj.shop.user.LoginSuccessHandler;
@Configuration // 스프링 환경 세팅을 돕는 어노테이션
@EnableWebSecurity // 스프링 시큐리티 설정할 클래스라고 알려주는 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    LoginIdPwValidator loginIdPwValidator;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable() // POST방식 허용
                .authorizeRequests()
                	.antMatchers( "/login", "/join","/","/product/insert","/**").permitAll() // 이 URI는 누구든 접근가능
                	//.antMatchers("/admin/**").hasRole("ADMIN") // ADMIN role만 접근 가능
                	//.antMatchers("/home").hasRole("USER") // ADMIN role만 접근 가능
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginProc") // 이 URI 호출시 스프링 시큐리티로 폼 정보를 제출 / form의 action
                    .usernameParameter("email") // 폼 input name값: default - username
                    .passwordParameter("userPwd") // 폼 input name값: default - password
                    .successHandler(loginSuccessHandler()) // 로그인 성공을 다룰 핸들러
                    .failureHandler(loginFailHandler()) // 로그인 실패를 다룰 핸들러
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/home") // 로그아웃 성공시 이동할 URL
        			.logoutRequestMatcher(new AntPathRequestMatcher("/logout") ); // 이 URI 호출시 로그아웃
    }
	
    //인증 예외 추가
    @Override
    public void configure(WebSecurity web) throws Exception {
    	 web.ignoring().antMatchers("/resources/**");
    	 web.httpFirewall(defaultHttpFirewall());
    }
	@Bean //url 더블 슬래시 허용 (이미지 불러오기 위해)
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

