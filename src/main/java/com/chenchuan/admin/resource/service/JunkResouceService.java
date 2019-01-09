package com.chenchuan.admin.resource.service;

/**
 * 垃圾数据清理service
 */
public interface JunkResouceService {

    /**
     * 安全垃圾数据清理
     *
     * @return 清理状态
     */
    int removeSecurityJunkData();

    /**
     * 博客业务垃圾数据清理
     *
     * @return 清理状态
     */
    int removeBlogBusiJunkData();
}
