package com.chenchuan.admin.blog.vo;

import com.chenchuan.admin.blog.po.ArticlePo;

/**
 * 文章vo
 */
public class ArticleVo extends ArticlePo {

    /**
     * 文章所有标签拼接成的字符串（一般为标签编号拼接，标签名称拼接的在sql前标注）
     */
    private String label;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
