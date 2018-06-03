package com.mingyuan.summer.controller;

import com.mingyuan.summer.domain.bean.Wisely2Settings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TestController {



    @Resource
    Wisely2Settings wisely2Settings;

    @RequestMapping("/test")
    public
    @ResponseBody
    String test() {

        System.out.println("wisely2.name = " + wisely2Settings.getName());
        System.out.println("wisely2.gender = " + wisely2Settings.getGender());
        System.out.println("wisely2.age = " + wisely2Settings.getAge());
        return "ok";
    }
}
