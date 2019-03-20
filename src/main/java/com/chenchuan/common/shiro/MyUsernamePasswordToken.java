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

    /**
     * 免密登录
     *
     * @param username
     * @param loginType
     */
    public MyUsernamePasswordToken(String username, String loginType) {
        super(username, "", false, null);
        this.loginType = loginType;
    }

    /**
     * 正常登陆
     *
     * @param username
     * @param password
     * @param loginType
     */
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
