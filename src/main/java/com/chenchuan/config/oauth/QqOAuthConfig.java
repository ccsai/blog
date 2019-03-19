package com.chenchuan.config.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * qq登录配置参数
 */
@Component
@ConfigurationProperties(prefix = "oauth.qq")
public class QqOAuthConfig {

    private String appId;

    private String appKey;

    /**
     * 获取ocde地址
     */
    private String getAuthorizationCodeUri;

    /**
     * 获取ocde的参数，固定为code
     */
    private String responseType;

    /**
     * 回调地址
     */
    private String redirectUri;

    /**
     * 获取Access Token地址
     */
    private String getAccessTokenUri;

    /**
     * 获取Access Token，固定为authorization_code
     */
    private String tokenGrantType;

    /**
     * 刷新Access Token，refresh_token
     */
    private String refreshTokenGrantType;

    /**
     * 获取openid的地址
     */
    private String getOpenidUri;

    /**
     * 获取用户信息地址
     */
    private String getUserInfoUri;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getGetAuthorizationCodeUri() {
        return getAuthorizationCodeUri;
    }

    public void setGetAuthorizationCodeUri(String getAuthorizationCodeUri) {
        this.getAuthorizationCodeUri = getAuthorizationCodeUri;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getGetAccessTokenUri() {
        return getAccessTokenUri;
    }

    public void setGetAccessTokenUri(String getAccessTokenUri) {
        this.getAccessTokenUri = getAccessTokenUri;
    }

    public String getTokenGrantType() {
        return tokenGrantType;
    }

    public void setTokenGrantType(String tokenGrantType) {
        this.tokenGrantType = tokenGrantType;
    }

    public String getRefreshTokenGrantType() {
        return refreshTokenGrantType;
    }

    public void setRefreshTokenGrantType(String refreshTokenGrantType) {
        this.refreshTokenGrantType = refreshTokenGrantType;
    }

    public String getGetOpenidUri() {
        return getOpenidUri;
    }

    public void setGetOpenidUri(String getOpenidUri) {
        this.getOpenidUri = getOpenidUri;
    }

    public String getGetUserInfoUri() {
        return getUserInfoUri;
    }

    public void setGetUserInfoUri(String getUserInfoUri) {
        this.getUserInfoUri = getUserInfoUri;
    }
}
