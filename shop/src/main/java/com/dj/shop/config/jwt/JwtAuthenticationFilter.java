package com.dj.shop.config.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dj.shop.vo.UserVO;

import lombok.RequiredArgsConstructor;

//스프링 시큐리티에서 login 요청해서 username ,password 전송하면 동작한다.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	
	// /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {  
			String email = request.getParameter("email");
		    String userPwd = request.getParameter("userPwd");

		    UsernamePasswordAuthenticationToken authenticationToken =
		            new UsernamePasswordAuthenticationToken(email, userPwd);

		    // UserService의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication이 리턴됨
		    // DB에 있는 username과 password가 일치하면
		    Authentication authentication =
		            authenticationManager.authenticate(authenticationToken);

		    // authentication 객체가 session 영역에 저장됨 => 로그인이 되었다
		    UserVO principalDetailis = (UserVO) authentication.getPrincipal();
			System.out.println("Authentication : "+principalDetailis.getUsername());
		    return authentication;
	}
	
	// 인증이 정상적으로 되었으면 실행
	// JWT 토큰을 만들어서 equest요청한 사용자에게 JWT 토큰을 response 해주면됨
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserVO userDetails =(UserVO) authResult.getPrincipal();
		
	    // JWT 토큰 생성
	    String jwt = generateJwtToken(userDetails);

	    // JWT 토큰을 응답 헤더에 추가
	    response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwt);
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
	
	private String generateJwtToken(UserVO userDetails) {
		String jwtToken = JWT.create()
	    		 .withSubject(userDetails.getUsername()) //토큰 이름
	    		 .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
	    		 .withClaim("id", userDetails.getUserNumber())
	    		 .withClaim("username", userDetails.getEmail())
	    		 .sign(Algorithm.HMAC512(JwtProperties.SECRET));
	     return jwtToken;
	             
	}
}
