package com.chenchuan.admin.blog.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.chenchuan.common.po.BasePo;

import java.util.Date;

/**
 * 文章评论po
 */
public class ArticleCommentPo extends BasePo {

    private String commentId;

    private String comment;

    private String articleId;

    private Integer isChecked;

    private Integer supportNumber;

    private Integer noSupportNumber;

    private String commentUserId;

    private String createUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String modifyUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String modifyTime;


    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getSupportNumber() {
        return supportNumber;
    }

    public void setSupportNumber(Integer supportNumber) {
        this.supportNumber = supportNumber;
    }

    public Integer getNoSupportNumber() {
        return noSupportNumber;
    }

    public void setNoSupportNumber(Integer noSupportNumber) {
        this.noSupportNumber = noSupportNumber;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
