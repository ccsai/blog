package com.chenchuan.admin.guest.controller;

import com.chenchuan.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台登录
 */
@Controller
@RequestMapping("/admin/guest/login")
public class AdminLoginController extends BaseController {

    /**
     * 后台登录首页
     *
     * @return 登录页面地址
     */
    @GetMapping("/index")
    public String index() {
        return "/admin/guest/login/index";
    }

}
