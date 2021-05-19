package com.rabbit.macro.mall.tiny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RabbitMallTiny03Application {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMallTiny03Application.class, args);
	}

}
