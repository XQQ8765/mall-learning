package com.rabbit.macro.mall.tiny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/r/brand")
public class PmsBrandController {

    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public String getBrandList() {
        return "listAll-brand-list";
    }
}
