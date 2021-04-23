package com.rabbit.macro.mall.tiny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = {"/hello", "/hi"},method = RequestMethod.GET)
    public String say(){
        return "Hello, rabbit!";
    }
}
