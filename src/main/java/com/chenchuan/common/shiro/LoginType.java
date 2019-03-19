package com.chenchuan.common.shiro;

/**
 * 登录方式
 */
public enum LoginType {

    /**
     * 密码登录
     */
    NORMAL("Normal"),

    /**
     * 免密登录
     */
    FREE("Free");


    LoginType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
