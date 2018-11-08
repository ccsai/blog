package com.chenchuan.common.controller;

import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
     * @return 登录结果
     */
    @PostMapping("login")
    @ResponseBody
    public Map<String, Object> login(UserVo userVo) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            loginService.login(userVo);
        } catch (UnknownAccountException e) {
            throw new BaseException("用户不存在");
        } catch (IncorrectCredentialsException e) {
            throw new BaseException("密码错误");
        } catch (AuthenticationException e) {
            throw new BaseException("用户名或密码错误");
        }
        resultMap.put("resultCode", 1);
        return resultMap;
    }

    /**
     * 登出
     */
    @GetMapping("/logOut")
    @ResponseBody
    public void logOut() {
        loginService.logOut();
    }
}
