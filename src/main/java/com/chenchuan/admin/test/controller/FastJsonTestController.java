package com.chenchuan.admin.test.controller;

import com.chenchuan.admin.sys.dao.UserDao;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FastJsonTestController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/findUser1")
    @ResponseBody
    public UserPo findUser1(UserVo userVo){
        System.out.println(userVo.getCreateTime());
        System.out.println(userVo.getStatus());
        System.out.println(userVo);
        return userVo;
    }
}
