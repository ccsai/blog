package com.chenchuan.admin.blog.dao;

import com.chenchuan.admin.blog.vo.ArticleCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 文章评论dao
 */
@Mapper
@Repository
public interface ArticleCommentDao {

    /**
     * 获取文章评论列表
     *
     * @param articleCommentVo
     * @return 文章评论列表
     */
    List<ArticleCommentVo> findArticleCommentList(ArticleCommentVo articleCommentVo);

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
     * @param isCheckedAndCommentIds 文章评论编号集合与修改后的状态
     * @return 影响行数
     */
    int editArticleCommentIsCheckedByArticleCommentIds(Map<String, Object> isCheckedAndCommentIds);

    /**
     * 根据文章评论编号集合批量删除文章评论
     *
     * @param commentIds 文章评论编号集合
     * @return 影响行数
     */
    int removeArticleCommentByArticleCommentIds(String[] commentIds);

    /**
     * 根据文章编号删除评论（包含删除评论oss关联）
     *
     * @param articleId 文章编号
     * @return 影响行数
     */
    int removeArticleCommentByArticleId(String articleId);

    /**
     * 添加文章品论
     *
     * @param articleCommentVo
     * @return 影响行数
     */
    int addarticleComment(ArticleCommentVo articleCommentVo);
}
