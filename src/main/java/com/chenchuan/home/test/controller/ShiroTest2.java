package com.chenchuan.home.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ShiroTest2 {

    @GetMapping("/shiroTest2")
    public void shiroTest2(){
        System.out.println("shiroTest2");
    }
}
