package com.chenchuan.admin.blog.service.impl;

import com.chenchuan.admin.blog.dao.ArticleCommentDao;
import com.chenchuan.admin.blog.dao.ArticleDao;
import com.chenchuan.admin.blog.dao.SupportDao;
import com.chenchuan.admin.blog.po.ArticlePo;
import com.chenchuan.admin.blog.service.ArticleService;
import com.chenchuan.admin.blog.vo.ArticleVo;
import com.chenchuan.admin.blog.vo.SupportVo;
import com.chenchuan.admin.resource.dao.OssDao;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章service实现
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleCommentDao articleCommentDao;

    @Autowired
    private OssDao ossDao;

    @Autowired
    private SupportDao supportDao;


    @Override
    public PageInfo<ArticleVo> findArticleByPage(ArticleVo articleVo) {
        PageHelper.startPage(articleVo.getPage(), articleVo.getRows());
        //文章列表
        List<ArticleVo> articleList = articleDao.findArticleList(articleVo);
        return new PageInfo<>(articleList);
    }

    @Override
    @Transactional
    public int addArticle(ArticleVo articleVo, String[] ossKeys) {
        //判断是否选择标签
        if (articleVo.getLabel() == null || articleVo.getLabel().equals("")) {
            throw new BaseException("请选择标签！");
        }
        articleVo.setArticleId(null);//用于判断标题重复
        //判断标题是否重复
        if (articleDao.findarticleTitleIsExists(articleVo) > 0) {
            throw new BaseException("该文章标题已经存在！");
        }
        //文章编号
        String articleId = UuidUtil.getUuid();
        articleVo.setArticleId(articleId);
        //当前登录用户
        String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
        articleVo.setModifyUser(userId);
        articleVo.setCreateUser(userId);
        //添加文章
        int affectRows = articleDao.addArticle(articleVo);
        if (affectRows <= 0) {
            throw new BaseException("文章添加失败！");
        }
        //添加文章标签关联
        addArticleLabelAuth(articleVo);
        //添加文章oss关联
        addArticleOssAuth(articleId, ossKeys);
        return 1;
    }

    @Override
    public ArticleVo findArticleDetailByArticleId(String articleId) {
        //文章详情
        ArticleVo articleDetail = articleDao.findArticleDetailByArticleId(articleId);
        if (articleDetail == null) {
            throw new BaseException("没有此文章！");
        }
        return articleDetail;
    }

    @Override
    @Transactional
    public int editArticleByArticleId(ArticleVo articleVo, String[] ossKeys) {
        //判断是否选择标签
        if (articleVo.getLabel() == null || articleVo.getLabel().equals("")) {
            throw new BaseException("请选择标签！");
        }
        //判断标题是否重复
        if (articleDao.findarticleTitleIsExists(articleVo) > 0) {
            throw new BaseException("该文章标题已经存在！");
        }
        //当前登录用户
        articleVo.setModifyUser(userService.getCurrentLoginUserBaseInfo().getUserId());
        //文章修改
        int affectRows = articleDao.editArticleByArticleId(articleVo);
        if (affectRows <= 0) {
            throw new BaseException("文章修改失败!");
        }
        //文章编号
        String articleId = articleVo.getArticleId();
        //删除历史文章标签关联
        articleDao.removeArticleLabelAuthByArticleId(articleId);
        //添加文章标签关联
        addArticleLabelAuth(articleVo);
        //删除历史文章oss关联
        removeArticleOssAuthByArticle(articleId);
        //添加新文章oss关联
        addArticleOssAuth(articleId, ossKeys);
        return 1;
    }

    @Override
    @Transactional
    public int removeArticleByArticleId(String articleId) {
        //删除文章
        int affectRows = articleDao.removeArticleByArticleId(articleId);
        if (affectRows <= 0) {
            throw new BaseException("删除文章失败！");
        }
        //刪除文章标签关联
        articleDao.removeArticleLabelAuthByArticleId(articleId);
        //删除文章评论（包含文章评论oss关联）
        articleCommentDao.removeArticleCommentByArticleId(articleId);
        //删除文章oss关联
        removeArticleOssAuthByArticle(articleId);
        return 1;
    }

    @Override
    public List<ArticleVo> findArticleList(ArticleVo articleVo) {
        return articleDao.findArticleList(articleVo);
    }

    @Override
    public List<ArticlePo> articleViewRanking() {
        return articleDao.articleViewRanking();
    }

    @Override
    public List<ArticlePo> findArticleListByCarousel() {
        return articleDao.findArticleListByCarousel();
    }

    @Override
    public PageInfo<ArticleVo> findArticleListForHome(ArticleVo articleVo) {
        PageHelper.startPage(articleVo.getPage(), articleVo.getRows());
        //文章列表
        List<ArticleVo> articleList = articleDao.findArticleListForHome(articleVo);
        return new PageInfo<>(articleList);
    }

    @Override
    public ArticleVo findArticleDetailwithLabelCommentByarticleId(String articleId) {
        //文章详情
        ArticleVo articleDetail = articleDao.findArticleDetailwithLabelCommentByarticleId(articleId);
        if (articleDetail == null) {
            throw new BaseException("该文章不存在");
        }
        //计算评论数量
        if (articleDetail.getArticleCommentList() == null || articleDetail.getArticleCommentList().size() == 0) {
            articleDetail.setCommentNumber(0);
        } else {
            articleDetail.setCommentNumber(articleDetail.getArticleCommentList().size());
        }
        return articleDetail;
    }

    @Override
    @Transactional
    public ArticlePo supportArticleByArticleId(ArticleVo articleVo) {
        //获取登录用户
        UserPo userPo = userService.getCurrentLoginUserBaseInfo();
        if (userPo == null) {
            throw new BaseException("获取用户信息异常");
        }
        //判断文章编号是否有值？
        String articleId = articleVo.getArticleId();
        if (articleId == null || articleId.equals("")) {
            throw new BaseException("没有选择文章");
        }
        //增加点赞次数
        articleVo.setSupportNumber(1);
        int affectRows = articleDao.supportArticleByArticleId(articleVo);
        if (affectRows <= 0) {
            throw new BaseException("点赞失败");
        }
        //增加点赞记录
        SupportVo supportVo = new SupportVo();
        supportVo.setSupportId(UuidUtil.getUuid());
        supportVo.setModuleId(articleId);
        supportVo.setModuleType(1);
        supportVo.setSupportType(1);
        supportVo.setCreateUser(userPo.getUserId());
        int supportLogAffectRows = supportDao.addSupportData(supportVo);
        if (supportLogAffectRows <= 0) {
            throw new BaseException("点赞失败，点赞记录失败");
        }
        //获取赞后的点赞信息
        ArticlePo articleInfo = articleDao.findSupportNumberByArticleId(articleId);
        if (articleInfo == null) {
            throw new BaseException("没有该文章");
        }
        return articleInfo;
    }

    /**
     * 添加文章标签关联
     *
     * @param articleVo
     */
    private void addArticleLabelAuth(ArticleVo articleVo) {
        //添加新的文章标签关联
        String labelStr = articleVo.getLabel();
        if (labelStr != null && labelStr.length() > 0) {
            //标签数组
            String[] labelIds = labelStr.split(",");
            //文章标签编号对应集合
            List<Map<String, Object>> articleLabelAuthList = new ArrayList<>();
            for (String labelId : labelIds) {
                Map<String, Object> articleLabelAuth = new HashMap<>();
                articleLabelAuth.put("articleId", articleVo.getArticleId());
                articleLabelAuth.put("labelId", labelId);
                articleLabelAuthList.add(articleLabelAuth);
            }
            //添加文章标签关联
            articleDao.addArticleLabelAuth(articleLabelAuthList);
        }
    }

    /**
     * 添加文章oss关联
     *
     * @param articleId 文章编号
     * @param ossKeys   oss key数组
     */
    private void addArticleOssAuth(String articleId, String[] ossKeys) {
        if (ossKeys != null && ossKeys.length > 0) {
            List<Map<String, Object>> articleOssAuthList = new ArrayList<>();
            for (String ossKey : ossKeys) {
                Map<String, Object> articleOssAuth = new HashMap<>();
                articleOssAuth.put("moduleId", articleId);
                articleOssAuth.put("ossKey", ossKey);
                articleOssAuth.put("moduleType", 2);
                articleOssAuthList.add(articleOssAuth);
            }
            //oss关联
            ossDao.addModuleOssAuth(articleOssAuthList);
        }
    }

    /**
     * 根据文章删除文章oss关联
     *
     * @param articleId 文章编号
     */
    private void removeArticleOssAuthByArticle(String articleId) {
        List<Map<String, Object>> articleOssAuthList = new ArrayList<>();
        Map<String, Object> articleOssAuth = new HashMap<>();
        articleOssAuth.put("moduleId", articleId);
        articleOssAuth.put("moduleType", 2);
        articleOssAuthList.add(articleOssAuth);
        //删除关联
        ossDao.removeModuleOssAuthByModule(articleOssAuthList);
    }
}
