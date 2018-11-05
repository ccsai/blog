package com.chenchuan.admin.test.controller;

import com.chenchuan.common.util.UuidUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UuidController {

    @RequestMapping("/getUuidTest")
    @ResponseBody
    public String[] getUuidTest(){
        return UuidUtil.getUuid(20);
    }
}
