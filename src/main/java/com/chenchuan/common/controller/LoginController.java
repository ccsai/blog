package com.chenchuan.common.controller;

import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.service.LoginService;
import com.chenchuan.common.util.UuidUtil;
import com.chenchuan.config.oauth.QqOAuthConfig;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录登出相关控制器
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private QqOAuthConfig qqOAuthConfig;


    /**
     * 登录
     *
     * @param userVo
     * @return 登录结果
     */
    @PostMapping("/login")
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
        //获取登录后的用户信息
        UserPo userInfo = userService.getCurrentLoginUserBaseInfo();
        if (userInfo == null) {
            throw new BaseException("登录异常");
        }
        resultMap.put("userInfo", userInfo);
        resultMap.put("resultCode", 1);
        return resultMap;
    }

    /**
     * qq登录获取Authorization Code
     *
     * @param httpServletResponse
     */
    @GetMapping("/login/qq")
    public void loginQQ(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.sendRedirect(qqOAuthConfig.getGetAuthorizationCodeUri() +
                    "?response_type=" + qqOAuthConfig.getResponseType() +
                    "&client_id=" + qqOAuthConfig.getAppId() +
                    "&redirect_uri=" + qqOAuthConfig.getRedirectUri() +
                    "&state=" + UuidUtil.getUuid());
        } catch (Exception e) {
            throw new BaseException("请求参数异常");
        }
    }

    /**
     * qq登陆回调
     *
     * @param code 授权获取的code
     * @return 登陆成功返回地址
     */
    @GetMapping("/login/qq/callback")
    @ResponseBody
    public String qqCallbackUri(String code) {
        loginService.qqLogin(code);
        return "success";
    }

    /**
     * 登出
     *
     * @return 登录成功标识
     */
    @GetMapping("/logOut")
    @ResponseBody
    public Map<String, Object> logOut() {
        Map<String, Object> resultMap = new HashMap<>();
        loginService.logOut();
        //验证是否登出成功
        UserPo userInfo = userService.getCurrentLoginUserBaseInfo();
        if (userInfo != null) {
            throw new BaseException("退出帐号异常");
        }
        resultMap.put("resultCode", 1);
        return resultMap;
    }
}
