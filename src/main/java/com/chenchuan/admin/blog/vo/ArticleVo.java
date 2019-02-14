package com.chenchuan.admin.blog.vo;

import com.chenchuan.admin.blog.po.ArticlePo;

import java.util.List;

/**
 * 文章vo
 */
public class ArticleVo extends ArticlePo {

    /**
     * 文章所有标签拼接成的字符串（一般为标签编号拼接，标签名称拼接的在sql前标注）
     */
    private String label;

    /**
     * 标签编号
     */
    private String labelId;

    /**
     * 查询关键字
     */
    private String keyword;

    /**
     * 评论数量
     */
    private Integer commentNumber;

    /**
     * 所属标签列表
     */
    private List<LabelVo> labelList;

    /**
     * 文章评论列表
     */
    private List<ArticleCommentVo> articleCommentList;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public List<LabelVo> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<LabelVo> labelList) {
        this.labelList = labelList;
    }

    public List<ArticleCommentVo> getArticleCommentList() {
        return articleCommentList;
    }

    public void setArticleCommentList(List<ArticleCommentVo> articleCommentList) {
        this.articleCommentList = articleCommentList;
    }
}
