package com.rabbit.macro.mall.tiny;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@MapperScan("com.rabbit.macro.mall.tiny.mbg.mapper")
public class RabbitMallTiny04Application {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMallTiny04Application.class, args);
	}

}
