package com.chenchuan.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * ajax请求工具类
 */
public class AjaxUtil {

    /**
     * 判断是否为ajax请求
     *
     * @param request
     * @return
     */
    public static Boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}
