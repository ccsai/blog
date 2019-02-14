package com.chenchuan.admin.guest.controller;

import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户controller
 */
@Controller(value = "adminGuestUserController")
@RequestMapping("/admin/guest")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 获取登录用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/findLoginUserInfo")
    @ResponseBody
    public Map<String, Object> findLoginUserInfo() {
        Map<String, Object> map = new HashMap<>();
        //登录用户信息
        map.put("userInfo", userService.getCurrentLoginUserBaseInfo());
        map.put("resultCode", 1);
        return map;
    }
}
