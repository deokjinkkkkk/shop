package com.dj.shop.config;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.dj.shop.interceptor.CartInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 외부 이미지 저장 경로를 설정한 프로퍼티
    @Value("${saveimg}")
    private String saveimg;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///" + saveimg)
                .setCachePeriod(20)  // 클라이언트 캐싱을 20초로 설정
                //한글이나 특수문자 가 들어가는 이미지 파일 읽을수 있게 해주기
                .resourceChain(true)  // 리소스 체인 사용
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        // URL에서 디코딩된 리소스 경로 생성
                        resourcePath = URLDecoder.decode(resourcePath, StandardCharsets.UTF_8);
                        // 생성된 리소스 반환
                        return location.createRelative(resourcePath);
                    }
                });
    }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(new CartInterceptor())
    //     .addPathPatterns("/**")
    //     .excludePathPatterns("/static/**");
    //     WebMvcConfigurer.super.addInterceptors(registry);
    // }
}
