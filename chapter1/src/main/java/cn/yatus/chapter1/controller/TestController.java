package cn.yatus.chapter1.controller;

import cn.yatus.chapter1.dto.CommonResponse;
import cn.yatus.chapter1.dto.UserDto;
import cn.yatus.chapter1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/write")
    public CommonResponse write(@RequestBody UserDto user) {
        userService.create(user);
        return CommonResponse.builder().code(0).success(true).build();
    }

    @RequestMapping("/read")
    public CommonResponse read() {
        return CommonResponse.builder().code(0).success(true)
                .result(userService.list()).build();
    }
}
