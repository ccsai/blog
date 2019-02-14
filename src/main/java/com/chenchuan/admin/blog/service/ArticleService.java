package com.chenchuan.admin.blog.service;

import com.chenchuan.admin.blog.po.ArticlePo;
import com.chenchuan.admin.blog.vo.ArticleVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 文章service
 */
public interface ArticleService {

    /**
     * 文章分页列表
     *
     * @param articleVo
     * @return 文章分页信息
     */
    PageInfo<ArticleVo> findArticleByPage(ArticleVo articleVo);

    /**
     * 添加文章
     *
     * @param articleVo
     * @return 添加状态
     */
    int addArticle(ArticleVo articleVo, String[] ossKeys);

    /**
     * 根据文章编号查看文章详情
     *
     * @param articleId
     * @return 文章详情
     */
    ArticleVo findArticleDetailByArticleId(String articleId);

    /**
     * 根据文章编号修改文章
     *
     * @param articleVo
     * @return 修改状态
     */
    int editArticleByArticleId(ArticleVo articleVo, String[] ossKeys);

    /**
     * 根据文章编号删除文章
     *
     * @param articleId 文章编号
     * @return 删除状态
     */
    int removeArticleByArticleId(String articleId);

    /**
     * 查询文章列表
     *
     * @param articleVo
     * @return 文章列表
     */
    List<ArticleVo> findArticleList(ArticleVo articleVo);

    /**
     * 文章浏览排名
     *
     * @return 文章列表
     */
    List<ArticlePo> articleViewRanking();

    /**
     * 首页轮播展示的文章
     *
     * @return 文章列表
     */
    List<ArticlePo> findArticleListByCarousel();

    /**
     * 前台文章列表分页展示
     *
     * @param articleVo
     * @return 文章分页列表
     */
    PageInfo<ArticleVo> findArticleListForHome(ArticleVo articleVo);

    /**
     * 根据文章编号查询文章详情（包括评论，标签）
     *
     * @param articleId 文章编号
     * @return 文章详情
     */
    ArticleVo findArticleDetailwithLabelCommentByarticleId(String articleId);

    /**
     * 根据文章编号点赞
     *
     * @param articleVo
     * @return 点赞后的点赞信息
     */
    ArticlePo supportArticleByArticleId(ArticleVo articleVo);
}
