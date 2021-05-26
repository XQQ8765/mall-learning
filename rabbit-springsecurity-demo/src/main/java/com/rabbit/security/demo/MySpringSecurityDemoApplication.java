package com.rabbit.security.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.rabbit.security.demo.mbg.mapper")
public class MySpringSecurityDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringSecurityDemoApplication.class, args);
    }
}
