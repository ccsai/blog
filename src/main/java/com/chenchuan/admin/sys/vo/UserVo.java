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


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
