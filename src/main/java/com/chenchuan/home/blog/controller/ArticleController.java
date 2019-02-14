package com.chenchuan.home.blog.controller;

import com.chenchuan.admin.blog.service.ArticleService;
import com.chenchuan.admin.blog.vo.ArticleVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 文章controller
 */
@Controller(value = "homeBlogArticleController")
@RequestMapping("/b.a")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;


    /**
     * 根据文章编号点赞
     *
     * @param articleVo
     * @return 文章点赞信息
     */
    @PostMapping("/supportArticle")
    @ResponseBody
    public Map<String, Object> supportArticle(ArticleVo articleVo) {
        Map<String, Object> map = new HashMap<>();
        //标签列表
        map.put("articleInfo", articleService.supportArticleByArticleId(articleVo));
        map.put("resultCode", 1);
        return map;
    }
}
