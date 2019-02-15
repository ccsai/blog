package com.chenchuan.admin.blog.service.impl;

import com.chenchuan.admin.blog.dao.ArticleCommentDao;
import com.chenchuan.admin.blog.dao.SupportDao;
import com.chenchuan.admin.blog.po.ArticleCommentPo;
import com.chenchuan.admin.blog.service.ArticleCommentService;
import com.chenchuan.admin.blog.vo.ArticleCommentVo;
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
 * 文章评论service实现类
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentDao articleCommentDao;

    @Autowired
    private UserService userService;

    @Autowired
    private OssDao ossDao;

    @Autowired
    private SupportDao supportDao;


    @Override
    public PageInfo<ArticleCommentVo> findArticleCommentByPage(ArticleCommentVo articleCommentVo) {
        PageHelper.startPage(articleCommentVo.getPage(), articleCommentVo.getRows());
        //评论列表
        List<ArticleCommentVo> articleCommentVoList = articleCommentDao.findArticleCommentList(articleCommentVo);
        return new PageInfo<>(articleCommentVoList);
    }

    @Override
    public ArticleCommentVo findArticleCommentDetailByArticleCommentId(String commentId) {
        return articleCommentDao.findArticleCommentDetailByArticleCommentId(commentId);
    }

    @Override
    @Transactional
    public int editArticleCommentIsCheckedByArticleCommentIds(Map<String, Object> isCheckedCommentIds) {
        //当前登录用编号
        String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
        isCheckedCommentIds.put("modifyUser", userId);
        //修改审核状态
        int affectRows = articleCommentDao.editArticleCommentIsCheckedByArticleCommentIds(isCheckedCommentIds);
        if (affectRows == 0) {
            throw new BaseException("没有审核状态改变");
        }
        return 1;
    }

    @Override
    @Transactional
    public int removeArticleCommentByArticleCommentIds(String[] commentIds) {
        //删除评论
        int affectRows = articleCommentDao.removeArticleCommentByArticleCommentIds(commentIds);
        if (affectRows == 0) {
            throw new BaseException("没有评论被删除");
        }
        //根据文章评论删除评论与oss关联
        if (commentIds != null && commentIds.length > 0) {
            List<Map<String, Object>> commentInfos = new ArrayList<>();
            for (String commentId : commentIds) {
                Map<String, Object> moduleInfo = new HashMap<>();
                moduleInfo.put("moduleId", commentId);
                moduleInfo.put("moduleType", 3);
                commentInfos.add(moduleInfo);
            }
            //删除评论oss关联
            ossDao.removeModuleOssAuthByModule(commentInfos);
        }
        return 1;
    }

    @Override
    @Transactional
    public int addArticleComment(ArticleCommentVo articleCommentVo, String[] ossKeys) {
        //判断是否选择文章
        if (articleCommentVo.getArticleId() == null || articleCommentVo.getArticleId().equals("")) {
            throw new BaseException("请选择文章！");
        }
        //判断是否输入文章评论类容
        if (articleCommentVo.getComment() == null || articleCommentVo.getComment().equals("")) {
            throw new BaseException("你输入的字符长度不能为0！");
        }
        //文章评论编号
        String commentId = UuidUtil.getUuid();
        articleCommentVo.setCommentId(commentId);
        //当前登录用户
        String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
        //评论人
        if (articleCommentVo.getCommentUserId() == null || !articleCommentVo.getCommentUserId().equals("manager")) {
            articleCommentVo.setCommentUserId(userId);
        }
        articleCommentVo.setCreateUser(userId);
        articleCommentVo.setModifyUser(userId);
        //添加评论
        return addArticleCommentToDB(articleCommentVo, ossKeys);
    }

    @Override
    @Transactional
    public int addArticleCommentByHome(ArticleCommentVo articleCommentVo, String[] ossKeys) {
        //判断是否选择文章
        if (articleCommentVo.getArticleId() == null || articleCommentVo.getArticleId().equals("")) {
            throw new BaseException("请选择评论文章！");
        }
        //判断是否输入文章评论类容
        if (articleCommentVo.getComment() == null || articleCommentVo.getComment().equals("")) {
            throw new BaseException("评论内容不能为空！");
        }
        //登录用户信息
        UserPo curUser = userService.getCurrentLoginUserBaseInfo();
        if (curUser == null) {
            throw new BaseException("获取登录用户信息出错！");
        }
        //登录用户编号
        String curUserId = curUser.getUserId();
        articleCommentVo.setCommentUserId(curUserId);
        articleCommentVo.setCreateUser(curUserId);
        articleCommentVo.setModifyUser(curUserId);
        //文章评论编号
        articleCommentVo.setCommentId(UuidUtil.getUuid());
        //添加评论
        return addArticleCommentToDB(articleCommentVo, ossKeys);
    }

    @Override
    @Transactional
    public ArticleCommentPo supportByArticleCommentId(ArticleCommentVo articleCommentVo) {
        //获取登录用户
        UserPo userPo = userService.getCurrentLoginUserBaseInfo();
        if (userPo == null) {
            throw new BaseException("获取用户信息异常");
        }
        //判断评论编号是否有值
        String articleCommentId = articleCommentVo.getCommentId();
        if (articleCommentId == null || articleCommentId.equals("")) {
            throw new BaseException("没有选择评论");
        }
        //增加点赞次数
        articleCommentVo.setSupportNumber(1);
        int supportAffectRows = articleCommentDao.supportByArticleCommentId(articleCommentVo);
        if (supportAffectRows <= 0) {
            throw new BaseException("点赞失败");
        }
        //增加点赞记录
        SupportVo supportVo = new SupportVo();
        supportVo.setSupportId(UuidUtil.getUuid());
        supportVo.setModuleId(articleCommentId);
        supportVo.setModuleType(2);
        supportVo.setSupportType(1);
        supportVo.setCreateUser(userPo.getUserId());
        int supportLogAffectRows = supportDao.addSupportData(supportVo);
        if (supportLogAffectRows <= 0) {
            throw new BaseException("点赞失败，点赞记录失败");
        }
        //点赞后的点赞信息
        ArticleCommentPo articleCommentInfo = articleCommentDao.findSupportNumberByArticleCommentId(articleCommentId);
        if (articleCommentInfo == null) {
            throw new BaseException("没有该评论");
        }
        return articleCommentInfo;
    }

    @Override
    @Transactional
    public ArticleCommentPo noSupportByArticleCommentId(ArticleCommentVo articleCommentVo) {
        //获取登录用户
        UserPo userPo = userService.getCurrentLoginUserBaseInfo();
        if (userPo == null) {
            throw new BaseException("获取用户信息异常");
        }
        //判断评论编号是否有值
        String articleCommentId = articleCommentVo.getCommentId();
        if (articleCommentId == null || articleCommentId.equals("")) {
            throw new BaseException("没有选择评论");
        }
        //增加踩次数
        articleCommentVo.setNoSupportNumber(1);
        int noSupprotAffectRows = articleCommentDao.noSupportByArticleCommentId(articleCommentVo);
        if (noSupprotAffectRows <= 0) {
            throw new BaseException("踩评论失败");
        }
        //增加踩记录
        SupportVo supportVo = new SupportVo();
        supportVo.setSupportId(UuidUtil.getUuid());
        supportVo.setModuleId(articleCommentId);
        supportVo.setModuleType(2);
        supportVo.setSupportType(2);
        supportVo.setCreateUser(userPo.getUserId());
        int noSupportLogRows = supportDao.addSupportData(supportVo);
        if (noSupportLogRows <= 0) {
            throw new BaseException("踩评论失败，踩记录失败");
        }
        //踩后的踩信息
        ArticleCommentPo articleCommentInfo = articleCommentDao.findNoSupportNumberByArticleCommentId(articleCommentId);
        if (articleCommentInfo == null) {
            throw new BaseException("没有该评论");
        }
        return articleCommentInfo;
    }

    /**
     * 添加文章评论到
     *
     * @param articleCommentVo
     * @param ossKeys
     * @return 添加状态
     */
    private int addArticleCommentToDB(ArticleCommentVo articleCommentVo, String[] ossKeys) {
        int affectRows = articleCommentDao.addarticleComment(articleCommentVo);
        if (affectRows <= 0) {
            throw new BaseException("发表评论失败!");
        }
        //添加评论oss关联
        List<Map<String, Object>> articleCommentOssAuths = new ArrayList<>();
        if (ossKeys != null && ossKeys.length > 0) {
            for (String ossKey : ossKeys) {
                Map<String, Object> articleCommentAuth = new HashMap<>();
                articleCommentAuth.put("moduleId", articleCommentVo.getCommentId());
                articleCommentAuth.put("ossKey", ossKey);
                articleCommentAuth.put("moduleType", 3);
                articleCommentOssAuths.add(articleCommentAuth);
            }
            //关联
            ossDao.addModuleOssAuth(articleCommentOssAuths);
        }
        return 1;
    }
}
