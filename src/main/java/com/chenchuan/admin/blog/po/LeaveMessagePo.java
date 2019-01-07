package com.chenchuan.admin.blog.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.chenchuan.common.po.BasePo;

import java.util.Date;

/**
 * 留言po
 */
public class LeaveMessagePo extends BasePo {

    private String leaveMessageId;

    private String message;

    private String targetUser;

    private Integer isRead;

    private String sendUser;

    private String createUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public String getLeaveMessageId() {
        return leaveMessageId;
    }

    public void setLeaveMessageId(String leaveMessageId) {
        this.leaveMessageId = leaveMessageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
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
}
