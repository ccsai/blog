package com.chenchuan.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义UsernamePasswordToken
 */
public class MyUsernamePasswordToken extends UsernamePasswordToken {

    /**
     * 登录方式
     */
    private String loginType;


    public MyUsernamePasswordToken() {
    }

    public MyUsernamePasswordToken(String username, String password, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
