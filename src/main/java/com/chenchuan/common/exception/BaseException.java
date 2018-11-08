package com.chenchuan.common.exception;

/**
 * 自定义基础异常格式
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }
}
