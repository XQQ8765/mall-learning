package com.macro.mall.tiny.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(tags = "EsProductController", description = "搜索商品管理")
@RequestMapping("/r/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;
}
