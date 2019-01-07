package com.chenchuan.admin.blog.controller;

import com.chenchuan.admin.blog.service.ArticleService;
import com.chenchuan.admin.blog.service.LabelService;
import com.chenchuan.admin.blog.vo.ArticleVo;
import com.chenchuan.admin.blog.vo.LabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 文章controller
 */
@Controller
@RequestMapping("/admin/blog/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LabelService labelService;


    @GetMapping("/index")
    public String index() {
        return "/admin/blog/article/index";
    }

    /**
     * 分页查询文章列表
     *
     * @param articleVo
     * @return 文章分页信息
     */
    @GetMapping("/findArticleByPage")
    @ResponseBody
    public Map<String, Object> findArticleByPage(ArticleVo articleVo) {
        Map<String, Object> map = new HashMap<>();
        //文章分页信息
        map.put("articlePageInfo", articleService.findArticleByPage(articleVo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 添加文章
     *
     * @param articleVo
     * @param ossKeys   oss key数组
     * @return 添加状态
     */
    @PostMapping("/addArticle")
    @ResponseBody
    public Map<String, Object> addArticle(ArticleVo articleVo, @RequestParam(value = "ossKeys[]", required = false) String[] ossKeys) {
        Map<String, Object> map = new HashMap<>();
        //文章添加
        map.put("resultCode", articleService.addArticle(articleVo, ossKeys));
        return map;
    }

    /**
     * 根据文章编号查看文章详情
     *
     * @param articleId 文章编号
     * @return 文章详情
     */
    @GetMapping("/findArticleDetailByArticleId")
    @ResponseBody
    public Map<String, Object> findArticleDetailByArticleId(String articleId) {
        Map<String, Object> map = new HashMap<>();
        //文章详情
        map.put("articleDetail", articleService.findArticleDetailByArticleId(articleId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据文章编号修改文章
     *
     * @param articleVo
     * @param ossKeys   oss key数组
     * @return 修改状态
     */
    @PostMapping("/editArticleByArticleId")
    @ResponseBody
    public Map<String, Object> editArticleByArticleId(ArticleVo articleVo, @RequestParam(value = "ossKeys[]", required = false) String[] ossKeys) {
        Map<String, Object> map = new HashMap<>();
        //文章修改
        map.put("resultCode", articleService.editArticleByArticleId(articleVo, ossKeys));
        return map;
    }

    /**
     * 根据文章编号删除文章
     *
     * @param articleId 文章编号
     * @return 删除状态
     */
    @PostMapping("/removeArticleByArticleId")
    @ResponseBody
    public Map<String, Object> removeArticleByArticleId(String articleId) {
        Map<String, Object> map = new HashMap<>();
        //文章删除
        map.put("resultCode", articleService.removeArticleByArticleId(articleId));
        return map;
    }

    /**
     * 标签列表
     *
     * @param labelVo
     * @return 标签列表
     */
    @GetMapping("/findLabelInArticle")
    @ResponseBody
    public Map<String, Object> findLabelInArticle(LabelVo labelVo) {
        Map<String, Object> map = new HashMap<>();
        //标签列表
        map.put("labelList", labelService.findLabelList(labelVo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 文章预览
     *
     * @param model
     * @param content 文章内容
     * @return 文章内容
     */
    @RequestMapping("/previewArticle")
    public String previewArticle(Model model, String content) {
        //标签列表
        model.addAttribute("content", content);
        return "/admin/templates/frontTemplate";
    }
}
