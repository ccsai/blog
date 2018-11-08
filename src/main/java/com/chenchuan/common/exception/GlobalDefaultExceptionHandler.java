package com.chenchuan.common.exception;

import com.chenchuan.common.util.AjaxUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    /**
     * 全局异常处理程序
     *
     * @param request
     * @param e
     * @return 异常提示信息
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Object gloabalExceptionHandler(HttpServletRequest request, RuntimeException e) {
        e.printStackTrace();
        if (AjaxUtil.isAjax(request)) {
            //ajax请求异常处理
            Map<String, Object> map = new HashMap<>();
            map.put("resultCode", -1);
            map.put("message", e.getMessage());
            return map;
        } else {
            //web请求异常出处理
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("message", e.getMessage());
            modelAndView.addObject("notice", "服务器繁忙!");
            modelAndView.setViewName("/common/exception/webRequestExceptionHandlerPage");
            return modelAndView;
        }
    }
}
