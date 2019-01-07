package com.chenchuan.admin.resource.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.chenchuan.common.po.BasePo;

import java.util.Date;

/**
 * 对象存储po
 */
public class OssPo extends BasePo {

    private String ossKey;

    private String ossMimeType;

    private Long ossSize;

    private String createUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String modifyUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String modifyTime;


    public String getOssKey() {
        return ossKey;
    }

    public void setOssKey(String ossKey) {
        this.ossKey = ossKey;
    }

    public String getOssMimeType() {
        return ossMimeType;
    }

    public void setOssMimeType(String ossMimeType) {
        this.ossMimeType = ossMimeType;
    }

    public Long getOssSize() {
        return ossSize;
    }

    public void setOssSize(Long ossSize) {
        this.ossSize = ossSize;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
