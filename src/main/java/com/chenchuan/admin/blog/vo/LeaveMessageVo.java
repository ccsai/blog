package com.chenchuan.admin.blog.vo;

import com.chenchuan.admin.blog.po.LeaveMessagePo;

/**
 * 留言vo
 */
public class LeaveMessageVo extends LeaveMessagePo {

    /**
     * 用户登录名
     */
    private String sendUserName;


    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }
}
