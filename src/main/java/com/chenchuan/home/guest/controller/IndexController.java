package com.chenchuan.home.guest.controller;

import com.chenchuan.admin.blog.vo.ArticleVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页controller
 */
@Controller(value = "homeIndex")
public class IndexController extends BaseController {

    /**
     * 文章分页列表每页显示数量
     */
    @Value("${page.home.article_rows}")
    private Integer articleRows;


    /**
     * 网站首页
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String defaultIndex(Model model) {
        //公共数据
        super.findCommonModule(model);
        //轮播
        model.addAttribute("carousel", commonArticleService.findArticleListByCarousel());
        //文章列表
        ArticleVo articleVo = new ArticleVo();
        articleVo.setPage(1);
        articleVo.setRows(articleRows);
        model.addAttribute("articlePageInfo", commonArticleService.findArticleListForHome(articleVo));
        //搜索方式
        model.addAttribute("searchType","empty");
        //搜索参数
        model.addAttribute("param","empty");
        return "/home/index";
    }
}
