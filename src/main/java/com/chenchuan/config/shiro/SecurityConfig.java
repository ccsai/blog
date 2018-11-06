package com.chenchuan.config.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载yml配置文件的方法
 */
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityConfig {

    /**
     * 加密方式
     */
    private String hashAlgorithmName;

    /**
     * 加密散列次数
     */
    private Integer hashIterations;

    /**
     * 盐加的后缀
     */
    private String saltSuffix;

    /**
     * 游客可访问的路径
     */
    private String[] anonUrlPath;


    public String getHashAlgorithmName() {
        return hashAlgorithmName;
    }

    public void setHashAlgorithmName(String hashAlgorithmName) {
        this.hashAlgorithmName = hashAlgorithmName;
    }

    public Integer getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(Integer hashIterations) {
        this.hashIterations = hashIterations;
    }

    public String getSaltSuffix() {
        return saltSuffix;
    }

    public void setSaltSuffix(String saltSuffix) {
        this.saltSuffix = saltSuffix;
    }

    public String[] getAnonUrlPath() {
        return anonUrlPath;
    }

    public void setAnonUrlPath(String[] anonUrlPath) {
        this.anonUrlPath = anonUrlPath;
    }
}
