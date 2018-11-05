package com.chenchuan.common.service.impl;

import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * 登录登出相关service实现
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String login(UserVo userVo) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        //收集用户登录凭证
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userVo.getLoginName(), userVo.getPassword());
        //认证
        subject.login(usernamePasswordToken);
        return "success";
    }

    @Override
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        //登出
        subject.logout();
        return "已退出";
    }


}
