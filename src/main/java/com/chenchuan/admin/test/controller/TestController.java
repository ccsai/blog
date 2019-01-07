package com.chenchuan.admin.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("tt")
@RequestMapping("/nn")
public class TestController {

    @GetMapping("aa")
    public String aa(){
        return "/admin/test/ueditor";
    }
}
