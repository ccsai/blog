package com.chenchuan.config.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载yml安全配置
 */
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityConfig {

    /**
     * 后台登录页面地址
     */
    private String adminLoginPageUrl;

    /**
     * 未授权提示界面地址
     */
    private String unauthorizedUrl;

    /**
     * 缓存管理器配置文件
     */
    private String cacheManagerConfigFile;

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


    public String getAdminLoginPageUrl() {
        return adminLoginPageUrl;
    }

    public void setAdminLoginPageUrl(String adminLoginPageUrl) {
        this.adminLoginPageUrl = adminLoginPageUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public String getCacheManagerConfigFile() {
        return cacheManagerConfigFile;
    }

    public void setCacheManagerConfigFile(String cacheManagerConfigFile) {
        this.cacheManagerConfigFile = cacheManagerConfigFile;
    }

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
