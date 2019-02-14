package com.chenchuan.home.blog.controller;

import com.chenchuan.admin.blog.service.ArticleCommentService;
import com.chenchuan.admin.blog.vo.ArticleCommentVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 文章评论controller
 */
@Controller(value = "homeBlogArticleCommentController")
@RequestMapping("/b.ac")
public class ArticleCommentController extends BaseController {

    @Autowired
    private ArticleCommentService articleCommentService;


    /**
     * 前台用户添加评论
     *
     * @param articleCommentVo
     * @param ossKeys
     * @return 评论状态
     */
    @PostMapping("/commentArticle")
    @ResponseBody
    public Map<String, Object> commentArticle(ArticleCommentVo articleCommentVo, @RequestParam(value = "ossKeys[]", required = false) String[] ossKeys) {
        Map<String, Object> map = new HashMap<>();
        //评论添加
        map.put("resultCode", articleCommentService.addArticleCommentByHome(articleCommentVo, ossKeys));
        return map;
    }
}
