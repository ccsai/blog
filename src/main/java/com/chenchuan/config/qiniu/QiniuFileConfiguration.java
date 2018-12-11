package com.chenchuan.config.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云oos配置
 */
@Configuration
public class QiniuFileConfiguration {

    /**
     * access_key
     */
    @Value("${qiniu.access_key}")
    private String accessKey;

    /**
     * secret_key
     */
    @Value("${qiniu.secret_key}")
    private String secretKey;


    /**
     * 华东机房,配置自己空间所在的区域
     *
     * @return
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
        return new com.qiniu.storage.Configuration(Zone.zone0());
    }

    /**
     * 上传工具实例
     *
     * @return
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiniuConfig());
    }

    /**
     * 认证信息实例
     *
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

    /**
     * 构建七牛云空间管理实例
     *
     * @return
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiniuConfig());
    }

    /**
     * 资源批操作实例
     *
     * @return
     */
    @Bean
    public BucketManager.BatchOperations batchOperations() {
        return new BucketManager.BatchOperations();
    }

    /**
     * 配置gson为json解析器
     *
     * @return
     */
    @Bean
    public Gson gson() {
        return new Gson();
    }
}
