package com.chenchuan.common.service;

import com.chenchuan.admin.sys.vo.UserVo;

/**
 * 登录登出相关service接口
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param userVo
     */
    void login(UserVo userVo);

    /**
     * qq登录
     *
     * @param code 获取token的code
     */
    void qqLogin(String code);

    /**
     * 登出
     */
    void logOut();
}
