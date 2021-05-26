package com.rabbit.security.demo.controller;

import com.rabbit.security.demo.JsonResult;
import com.rabbit.security.demo.ResultTool;
import com.rabbit.security.demo.mbg.model.SysUser;
import com.rabbit.security.demo.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "UserController", description = "用户管理")
@RestController
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/r/getUser")
    @ApiOperation("根据用户名获取用户信息")
    public JsonResult getUser(String username) {
        SysUser sysUser = sysUserService.selectByName(username);
        return ResultTool.success(sysUser);
    }

    @GetMapping("/r/test")
    @ApiOperation("an api for test")
    public JsonResult test() {
        return ResultTool.success("hello world");
    }
}
