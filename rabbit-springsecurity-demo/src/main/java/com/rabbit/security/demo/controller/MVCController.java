package com.rabbit.security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * See:
 * spring boot + thymeleaf +security自定义规则 的简单使用 https://www.cnblogs.com/c2g5201314/p/13030335.html
 *
 * 对应的html page定义在 /resources/templates 目录下
 */
@Controller
public class MVCController {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    //心得，index.html默认是首页，当没有指定路径 / 是哪个文件时 index.html将默认是根路径/
}
