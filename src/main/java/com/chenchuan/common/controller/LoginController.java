package com.chenchuan.common.controller;

import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录登出相关控制器
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param userVo
     * @return
     */
    @GetMapping("login")
    @ResponseBody
    public String login(UserVo userVo) {
        return loginService.login(userVo);
    }

    @GetMapping("/logOut")
    public String logOut() {
        return loginService.logOut();
    }
}
