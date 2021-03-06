package com.chenchuan.admin.sys.vo;

import com.chenchuan.admin.sys.po.UserPo;

/**
 * 用户vo
 */
public class UserVo extends UserPo {

    /**
     * 查询关键字
     */
    private String keyWord;

    /**
     * 未读留言
     */
    private Integer leaveMessageNumber;

    /**
     * 确认的密码
     */
    private String twicePassword;


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getLeaveMessageNumber() {
        return leaveMessageNumber;
    }

    public void setLeaveMessageNumber(Integer leaveMessageNumber) {
        this.leaveMessageNumber = leaveMessageNumber;
    }

    public String getTwicePassword() {
        return twicePassword;
    }

    public void setTwicePassword(String twicePassword) {
        this.twicePassword = twicePassword;
    }
}
