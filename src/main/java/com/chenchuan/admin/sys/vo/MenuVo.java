package com.chenchuan.admin.sys.vo;

import com.chenchuan.admin.sys.po.MenuPo;

import java.util.List;

/**
 * 菜单vo
 */
public class MenuVo extends MenuPo {

    /**
     * 菜单查询关键字
     */
    private String keyWord;

    /**
     * 子菜单个数
     */
    private Integer childrenNumber;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 树的唯一编号
     */
    private String id;

    /**
     * 树节点显示内容
     */
    private String text;

    /**
     * 菜单是否选中
     */
    private Integer menuChecked;

    /**
     * 对应权限集合
     */
    private List<PermissionVo> permissionList;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(Integer childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getMenuChecked() {
        return menuChecked;
    }

    public void setMenuChecked(Integer menuChecked) {
        this.menuChecked = menuChecked;
    }

    public List<PermissionVo> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionVo> permissionList) {
        this.permissionList = permissionList;
    }
}
