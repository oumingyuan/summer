package com.mingyuan.summer.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mingyuan.summer.domain.UserInfo;
import com.mingyuan.summer.mapper.UserInfoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


@RestController
@RequestMapping(value = "/login")
public class UserInfoController {

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    /**
     * 登录操作
     *
     * @param loginData 登录信息
     * @return 返回登录的数据
     */
    @RequestMapping(value = "/getUser")
    public Map<String, Object> getOne(@RequestBody JSONObject loginData) {

        Map<String, Object> resultMap = new HashMap<>();

        String code = loginData.getString("code");

        String password = loginData.getString("password");

        List<UserInfo> userInfoList = userInfoJpaRepository.findAll();

        List<String> codeList = new LinkedList<>();

        for (UserInfo userInfo : userInfoList) {

            codeList.add(userInfo.getCode());

        }

        if (codeList.contains(code)) {

            for (UserInfo userInfo : userInfoList) {

                if (userInfo.getCode().equals(code)) {

                    String passwordInDatabase = userInfo.getPassword();

                    if (passwordInDatabase.equals(password)) {

                        resultMap.put("result", "success");
                        resultMap.put("code", code);
                    } else {
                        resultMap.put("result", "password error");
                    }

                }

            }

        } else {

            resultMap.put("result", "code not in database");
        }

        System.out.println(resultMap);

        return resultMap;

    }

    @RequestMapping(value = "/getInfo")
    public Map<String, Object> getInfo(@RequestBody JSONObject loginData) {

        Map<String, Object> resultMap = new HashMap<>();

        System.out.println("get information");

        UserInfo userInfo = userInfoJpaRepository.getOne(loginData.getString("code"));

        resultMap.put("information", userInfo.getLogInfo());
        resultMap.put("result", "success");

        System.out.println(resultMap);

        return resultMap;

    }

    @RequestMapping(value = "/saveInfo")
    public Map<String, Object> saveInfo(@RequestBody JSONObject loginData) {

        Map<String, Object> resultMap = new HashMap<>();

        String info = loginData.getString("info");

        UserInfo userInfo = userInfoJpaRepository.getOne(loginData.getString("code"));

        userInfo.setLogInfo(info);

        userInfoJpaRepository.saveAndFlush(userInfo);

        resultMap.put("result", "success");

        System.out.println(resultMap);

        return resultMap;

    }


}
