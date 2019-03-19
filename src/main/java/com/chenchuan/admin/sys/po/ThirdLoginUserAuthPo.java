package com.chenchuan.admin.sys.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.chenchuan.common.po.BasePo;

import java.util.Date;

/**
 * 三方登录用户信息po
 */
public class ThirdLoginUserAuthPo extends BasePo {

    /**
     * 主键
     */
    private String thridId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 唯一标识
     */
    private String openid;

    private String chatHead;

    /**
     * 三方登录平台（qq、微信）
     */
    private String type;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;


    public String getThridId() {
        return thridId;
    }

    public void setThridId(String thridId) {
        this.thridId = thridId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getChatHead() {
        return chatHead;
    }

    public void setChatHead(String chatHead) {
        this.chatHead = chatHead;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
