package com.chenchuan.config.exception;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载yml异常配置
 */
@Component
@ConfigurationProperties(prefix = "exception")
public class ExceptionConfig {

    /**
     * 异常处理页面
     */
    private String globalDefaultExceptionHandlerPage;


    public String getGlobalDefaultExceptionHandlerPage() {
        return globalDefaultExceptionHandlerPage;
    }

    public void setGlobalDefaultExceptionHandlerPage(String globalDefaultExceptionHandlerPage) {
        this.globalDefaultExceptionHandlerPage = globalDefaultExceptionHandlerPage;
    }
}
