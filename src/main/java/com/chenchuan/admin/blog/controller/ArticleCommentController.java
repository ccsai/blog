package com.chenchuan.admin.blog.controller;

import com.chenchuan.admin.blog.service.ArticleCommentService;
import com.chenchuan.admin.blog.service.ArticleService;
import com.chenchuan.admin.blog.vo.ArticleCommentVo;
import com.chenchuan.admin.blog.vo.ArticleVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 文章评论controller
 */
@Controller
@RequestMapping("/admin/blog/articleComment")
public class ArticleCommentController extends BaseController {

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private ArticleService articleService;


    @GetMapping("/index")
    public String index() {
        return "/admin/blog/article_comment/index";
    }

    /**
     * 文章评论分页
     *
     * @param articleCommentVo
     * @return 评论分页信息
     */
    @GetMapping("/findArticleCommentByPage")
    @ResponseBody
    public Map<String, Object> findArticleCommentByPage(ArticleCommentVo articleCommentVo) {
        Map<String, Object> map = new HashMap<>();
        //分页查询
        map.put("articleCommentPageInfo", articleCommentService.findArticleCommentByPage(articleCommentVo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据文章评论编号查询评论详情
     *
     * @param commentId 文章评论编号
     * @return 文章评论详情
     */
    @GetMapping("/findArticleCommentDetailByArticleCommentId")
    @ResponseBody
    public Map<String, Object> findArticleCommentDetailByArticleCommentId(String commentId) {
        Map<String, Object> map = new HashMap<>();
        //查询详情
        map.put("articleCommentDetail", articleCommentService.findArticleCommentDetailByArticleCommentId(commentId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据文章评论编号（逗号分隔的编号字符串）批量修改评论审核状态
     *
     * @param isCheckedCommentIds 文章评论编号集合与修改后的状态
     * @return 修改结果状态
     */
    @PostMapping("/editArticleCommentIsCheckedByArticleCommentIds")
    @ResponseBody
    public Map<String, Object> editArticleCommentIsCheckedByArticleCommentIds(@RequestParam Map<String, Object> isCheckedCommentIds) {
        Map<String, Object> map = new HashMap<>();
        //评论修改状态
        map.put("resultCode", articleCommentService.editArticleCommentIsCheckedByArticleCommentIds(isCheckedCommentIds));
        return map;
    }

    /**
     * 根据文章评论编号集合批量删除文章评论
     *
     * @param commentIds 文章评论编号集合
     * @return 删除状态码
     */
    @PostMapping("/removeArticleCommentByArticleCommentIds")
    @ResponseBody
    public Map<String, Object> removeArticleCommentByArticleCommentIds(@RequestParam("commentIds[]") String[] commentIds) {
        Map<String, Object> map = new HashMap<>();
        //评论删除
        map.put("resultCode", articleCommentService.removeArticleCommentByArticleCommentIds(commentIds));
        return map;
    }

    /**
     * 添加评论
     *
     * @param articleCommentVo
     * @return 添加状态
     */
    @PostMapping("/addArticleComment")
    @ResponseBody
    public Map<String, Object> addArticleComment(ArticleCommentVo articleCommentVo, @RequestParam(value = "ossKeys[]", required = false) String[] ossKeys) {
        Map<String, Object> map = new HashMap<>();
        //评论添加
        map.put("resultCode", articleCommentService.addArticleComment(articleCommentVo, ossKeys));
        return map;
    }

    /**
     * 查找文章列表
     *
     * @param articleVo
     * @return 文章列表
     */
    @GetMapping("/findArticleList")
    @ResponseBody
    public Map<String, Object> findArticleList(ArticleVo articleVo) {
        Map<String, Object> map = new HashMap<>();
        //文章列表
        map.put("articleList", articleService.findArticleList(articleVo));
        map.put("resultCode", 1);
        return map;
    }
}
