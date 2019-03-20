package com.chenchuan.home.guest.controller;

import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户controller
 */
@Controller(value = "guestHomeUserController")
@RequestMapping("/g.u")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 用户注册
     *
     * @param userVo
     * @return 注册状态
     */
    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(UserVo userVo) {
        Map<String, Object> map = new HashMap<>();
        //注册
        map.put("resultCode", userService.register(userVo));
        return map;
    }
}
