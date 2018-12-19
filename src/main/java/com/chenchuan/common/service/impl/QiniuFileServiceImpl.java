package com.chenchuan.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.service.QiniuFileService;
import com.chenchuan.common.util.UuidUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

@Service
public class QiniuFileServiceImpl implements QiniuFileService {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Autowired
    private BucketManager.BatchOperations batchOperations;

    /**
     * 空间
     */
    @Value("${qiniu.bucket}")
    private String bucket;

    /**
     * 上传过期时间
     */
    @Value("${qiniu.up_expire_seconds}")
    private Long upExpireSeconds;

    /**
     * 七牛CDN前缀
     */
    @Value("${qiniu.cdn_prefix}")
    private String cdnPrefix;

    /**
     * 定义七牛云上传的相关策略
     */
    private StringMap putPolicy;


    @Override
    @Transactional
    public JSONObject uploadFile(InputStream inputStream) throws IOException {
        //判断文件是否为空
        if (inputStream == null || inputStream.available() == 0) {
            throw new BaseException("请选择上传文件！");
        }
        //返回设置
        JSONObject resultOfPolicy = null;
        Response response = null;
        try {
            //上传文件
            response = uploadManager.put(inputStream,
                    UuidUtil.getUuid(),
                    getUploadToken(),
                    null,
                    null);
            //解析上传成功的结果
            resultOfPolicy = JSON.parseObject(response.bodyString());
        } catch (QiniuException e) {//异常要删除已经上传的文件
            deleteFile(resultOfPolicy.getString("key"));
            throw new BaseException("文件上传失败！<br>" + e.response.toString());
        } catch (Exception e) {
            deleteFile(resultOfPolicy.getString("key"));
            throw new BaseException("文件上传失败！");
        }
        return resultOfPolicy;
    }

    @Override
    @Transactional
    public int deleteFile(String key) {
        if (key == null) {
            throw new BaseException("请选择要删除的文件");
        }
        //上传结果状态
        int result = 0;
        Response response = null;
        try {
            //删除文件
            response = bucketManager.delete(bucket, key);
            if (response.statusCode == 200) {
                result = 1;
            }
        } catch (QiniuException e) {
            throw new BaseException("文件删除失败！<br>" + e.code() + "<br>" + e.response.toString());
        }
        return result;
    }

    @Override
    public int deleteFiles(String[] keysList) {
        if (keysList == null || keysList.length == 0) {
            throw new BaseException("请选择要删除的文件");
        }
        //删除结果
        int result = 0;
        batchOperations.addDeleteOp(bucket, keysList);
        try {
            Response response = bucketManager.batch(batchOperations);
            if (response.statusCode == 200) {
                result = 1;
            }
        } catch (QiniuException e) {
            throw new BaseException("文件删除失败！<br>" + e.response.toString());
        }
        return result;
    }

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken() {
        return auth.uploadToken(bucket, null, upExpireSeconds, uploadPolicy());
    }

    /**
     * 文件上传的回调
     */
    private StringMap uploadPolicy() {
        putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"imageInfo\":$(imageInfo),\"cdnPrefix\":\"" + cdnPrefix + "\"}");
        return putPolicy;
    }
}