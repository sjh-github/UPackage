package com.wwdlb.hongruan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@MapperScan(basePackages = "com.wwdlb.hongruan")
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
public class HongruanApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HongruanApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(HongruanApplication.class);
	}

}
