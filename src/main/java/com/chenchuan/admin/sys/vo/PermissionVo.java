package com.chenchuan.admin.sys.vo;

import com.chenchuan.admin.sys.po.PermissionPo;

/**
 * 权限vo
 */
public class PermissionVo extends PermissionPo {

    /**
     * 所属菜单名称
     */
    private String menuName;

    /**
     * 查询关键字
     */
    private String keyWord;


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
