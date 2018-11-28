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

    /**
     * 权限是否选中
     */
    private Integer permissionChecked;


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

    public Integer getPermissionChecked() {
        return permissionChecked;
    }

    public void setPermissionChecked(Integer permissionChecked) {
        this.permissionChecked = permissionChecked;
    }
}
