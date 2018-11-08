package com.chenchuan.common.service;

import com.chenchuan.admin.sys.vo.UserVo;

/**
 * 登录登出相关service接口
 */
public interface LoginService {

    /**
     * 登录
     *
     * @return 登录是否成功
     */
    void login(UserVo userVo);

    /**
     * 登出
     */
    void logOut();
}
