package com.wwdlb.hongruan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages = "com.wwdlb.hongruan")
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
public class HongruanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HongruanApplication.class, args);
	}
}
