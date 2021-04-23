package com.rabbit.macro.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.rabbit.macro.mall.tiny.mbg.mapper")
public class MyBatisConfig {
}
