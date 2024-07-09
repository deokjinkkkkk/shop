package com.dj.shop.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dj.shop.filter.JwtFilter;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtfilter(){
		FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>(new JwtFilter());
		bean.addUrlPatterns("/**");
		bean.setOrder(0);
		return bean;
	}
}
