package com.chenchuan.admin.blog.vo;

import com.chenchuan.admin.blog.po.FriendlyLinkPo;

/**
 * 友情链接vo
 */
public class FriendlyLinkVo extends FriendlyLinkPo {

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
