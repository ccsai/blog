package com.chenchuan.common.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共Controller
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获取http请求对象
     *
     * @return http请求对象
     */
    protected HttpServletRequest getRequest() {
        return request;
    }
}
