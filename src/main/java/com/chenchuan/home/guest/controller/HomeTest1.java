package com.chenchuan.home.guest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/guest")
public class HomeTest1 {

    @GetMapping("/shiroTest1")
    @ResponseBody
    public void shiroTest1(){
        System.out.println("shiroTest1");
    }
}
