package com.chenchuan.admin.sys.vo;

import com.chenchuan.admin.sys.po.MenuPo;

/**
 * 菜单vo
 */
public class MenuVo extends MenuPo {

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
}
