package com.chenchuan.common.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛oos Service
 */
public interface QiniuFileService {

    /**
     * 单文件上传
     *
     * @param inputStream
     * @return 上传返回信息
     */
    JSONObject uploadFile(InputStream inputStream) throws IOException;

    /**
     * 单文件删除文件
     *
     * @param key 文件key
     * @return 删除结果状态
     */
    int deleteFile(String key);

    /**
     * 文件批量删除
     *
     * @param keysList 文件key集合
     * @return
     */
    int deleteFiles(String[] keysList);

}