package com.dj.shop.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfig {
	
    @Value("${spring.mail.username}")
    private String id;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;

	@Bean
	public JavaMailSender eMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(host); //  smtp 서버 주소
		javaMailSender.setUsername(id); // 구글 아이디
		javaMailSender.setPassword(password); // 구글 비밀번호
		javaMailSender.setPort(port); // 메일 인증서버 포트
		javaMailSender.setJavaMailProperties(getMailProperties()); // 메일 인증서버 정보 가져오기

		return javaMailSender;
	}

	private Properties getMailProperties() { //메일 인증서버 정보
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
		properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
		properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp strattles 사용
		properties.setProperty("mail.debug", "true"); // 디버그 사용
		return properties;
	}

	
	
	
	
	
	
}