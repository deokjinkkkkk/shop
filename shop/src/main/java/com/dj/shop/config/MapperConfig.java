package com.dj.shop.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
@MapperScan(basePackages = "com.dj.shop.mapper")
public class MapperConfig {
	
}
