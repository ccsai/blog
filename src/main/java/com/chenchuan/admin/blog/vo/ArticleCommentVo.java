package com.chenchuan.admin.blog.vo;

import com.chenchuan.admin.blog.po.ArticleCommentPo;

/**
 * 文章评论vo
 */
public class ArticleCommentVo extends ArticleCommentPo {

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 评论人
     */
    private String commentUser;


    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }
}
