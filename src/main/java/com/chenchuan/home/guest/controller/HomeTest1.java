package com.chenchuan.home.guest.controller;

import com.chenchuan.common.util.HashedUtil;
import com.chenchuan.config.shiro.SecurityConfig;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/guest")
public class HomeTest1 {

    @Autowired
    private SecurityConfig securityConfig;


    @GetMapping("/shiroTest1")
    @ResponseBody
    public void shiroTest1() {
        System.out.println("shiroTest1");
    }

    @GetMapping("md5Test")
    @ResponseBody
    public void md5() {
        SimpleHash simpleHash = HashedUtil.getSimpleHash("admin", "111111", securityConfig.getHashAlgorithmName(), securityConfig.getHashIterations());
        SimpleHash b = HashedUtil.getSimpleHash("userTest", "333333", securityConfig.getHashAlgorithmName(), securityConfig.getHashIterations());
        System.out.println(simpleHash);
        System.out.println(b);
    }
}
