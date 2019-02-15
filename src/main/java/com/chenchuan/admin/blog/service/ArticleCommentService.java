package com.chenchuan.admin.blog.service;

import com.chenchuan.admin.blog.po.ArticleCommentPo;
import com.chenchuan.admin.blog.vo.ArticleCommentVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 文章评论service
 */
public interface ArticleCommentService {

    /**
     * 分页查询文章评论列表
     *
     * @param articleCommentVo
     * @return 评论分页信息
     */
    PageInfo<ArticleCommentVo> findArticleCommentByPage(ArticleCommentVo articleCommentVo);

    /**
     * 根据文章评论编号查询评论详情
     *
     * @param commentId 文章评论编号
     * @return 文章评论详情
     */
    ArticleCommentVo findArticleCommentDetailByArticleCommentId(String commentId);

    /**
     * 根据文章评论编号（逗号分隔的编号字符串）批量修改评论审核状态
     *
     * @param isCheckedCommentIds 文章评论编号集合与修改后的状态
     * @return 修改结果状态
     */
    int editArticleCommentIsCheckedByArticleCommentIds(Map<String, Object> isCheckedCommentIds);

    /**
     * 根据文章评论编号集合批量删除文章评论
     *
     * @param commentIds 文章评论编号集合
     * @return 删除状态码
     */
    int removeArticleCommentByArticleCommentIds(String[] commentIds);

    /**
     * 添加文章评论
     *
     * @param articleCommentVo
     * @param ossKeys
     * @return 添加状态
     */
    int addArticleComment(ArticleCommentVo articleCommentVo, String[] ossKeys);

    /**
     * 前台用户添加文章评论
     *
     * @param articleCommentVo
     * @param ossKeys
     * @return 添加状态
     */
    int addArticleCommentByHome(ArticleCommentVo articleCommentVo, String[] ossKeys);

    /**
     * 根据文章评论编号点赞
     *
     * @param articleCommentVo
     * @return 文章评论点赞信息
     */
    ArticleCommentPo supportByArticleCommentId(ArticleCommentVo articleCommentVo);

    /**
     * 根据文章评论编号踩
     *
     * @param articleCommentVo
     * @return 文章评论踩信息
     */
    ArticleCommentPo noSupportByArticleCommentId(ArticleCommentVo articleCommentVo);
}
