package com.dj.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class CartInterceptor implements HandlerInterceptor{

    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 사용자 회원만 사용할수 있게 권한체크 하기 
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}