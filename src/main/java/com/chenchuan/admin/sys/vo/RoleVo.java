package com.chenchuan.admin.sys.vo;

import com.chenchuan.admin.sys.po.RolePo;

/**
 * 角色vo
 */
public class RoleVo extends RolePo {

    /**
     * 菜单查询关键字
     */
    private String keyWord;


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
