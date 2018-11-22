package com.chenchuan.common.po;

/**
 * 公共Po
 */
public class BasePo {

    /**
     * 当前页码
     */
    private Integer page;

    /**
     * 当前页显示条数
     */
    private Integer rows;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
