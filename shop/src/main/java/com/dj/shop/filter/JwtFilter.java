package com.dj.shop.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req  = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//토큰 : CORS  id,pw가 로그인이 완료되면  토큰을 만들어주고
		// 요청할 때마다 header에 Authorization에 value 값으로 토큰을 가지고옮
		//그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는 지만 검증 한다.
		if(req.getMethod().equals("POST")) {
			System.out.println("POST 요청됨");
			String headerAuth = req.getHeader("Authorization");
			System.out.println(headerAuth);
			if(headerAuth.equals("cors")) {
				chain.doFilter(req, res);
			}else {
				PrintWriter out= res.getWriter();
				out.println("인증안됨");
			}
		}
		
		System.out.println("필터1");
	
		
		
	}

}
