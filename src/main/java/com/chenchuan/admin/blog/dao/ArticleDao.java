package com.chenchuan.admin.blog.dao;

import com.chenchuan.admin.blog.po.ArticlePo;
import com.chenchuan.admin.blog.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 文章dao
 */
@Mapper
@Repository
public interface ArticleDao {

    /**
     * 获取文章列表
     *
     * @param articleVo
     * @return 文章列表
     */
    List<ArticleVo> findArticleList(ArticleVo articleVo);

    /**
     * 查询文章标题是否存在返回个数
     *
     * @param articleVo
     * @return 行数
     */
    int findarticleTitleIsExists(ArticleVo articleVo);

    /**
     * 添加文章
     *
     * @param articleVo
     * @return 影响行数
     */
    int addArticle(ArticleVo articleVo);

    /**
     * 添加文章标签关联
     *
     * @param articleLabelAuthList 文章标签编号关联
     * @return 影响行数
     */
    int addArticleLabelAuth(List<Map<String, Object>> articleLabelAuthList);

    /**
     * 根据文章编号查看文章详情
     *
     * @param articleId 文章编号
     * @return 文章详情
     */
    ArticleVo findArticleDetailByArticleId(String articleId);

    /**
     * 根据文章编号修改文章
     *
     * @param articleVo
     * @return 影响行数
     */
    int editArticleByArticleId(ArticleVo articleVo);

    /**
     * 根据文章编号删除文章标签关联
     *
     * @param articleId 文章编号
     * @return 影响行数
     */
    int removeArticleLabelAuthByArticleId(String articleId);

    /**
     * 根据文章编号删除文章
     *
     * @param articleId 文章编号
     * @return 影响行数
     */
    int removeArticleByArticleId(String articleId);

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
     * 前台文章列表展示
     *
     * @param articleVo
     * @return 文章列表
     */
    List<ArticleVo> findArticleListForHome(ArticleVo articleVo);

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
     * @return 影响行数
     */
    int supportArticleByArticleId(ArticleVo articleVo);

    /**
     * 根据文章编号查询点赞次数
     *
     * @param articleId 文章编号
     * @return 文章点赞信息
     */
    ArticlePo findSupportNumberByArticleId(String articleId);

    /**
     * 根据文章编号增加访问次数
     *
     * @param articleVo
     * @return 影响行数
     */
    int addVisitedNumberByArticleId(ArticleVo articleVo);
}
