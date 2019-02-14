package com.chenchuan.home.guest.controller;

import com.chenchuan.admin.blog.vo.ArticleVo;
import com.chenchuan.common.controller.BaseController;
import com.chenchuan.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文章controller
 */
@Controller(value = "guestHomeArticleController")
@RequestMapping("/g.a")
public class ArticleController extends BaseController {

    /**
     * 文章分页列表每页显示数量
     */
    @Value("${page.home.article_rows}")
    private Integer articleRows;


    /**
     * 文章列表（主页外的模块）
     *
     * @param model
     * @param bannerMark 文章列表所属banner
     * @param page       显示页码
     * @return 文章分页信息
     */
    @GetMapping("/list/{bannerMark}/page/{page}")
    public String findArticleListByPage(Model model, @PathVariable String bannerMark, @PathVariable Integer page) {
        //公共数据
        super.findCommonModule(model);
        //banner名称
        String bannerName;
        //文章查询参数
        ArticleVo articleVo = new ArticleVo();
        switch (bannerMark) {
            case "sc":
                bannerName = "技术交流";
                articleVo.setBanner("1");
                break;
            case "ll":
                bannerName = "生活日志";
                articleVo.setBanner("2");
                break;
            case "hl":
                bannerName = "兴趣爱好";
                articleVo.setBanner("3");
                break;
            case "a":
                bannerName = "关于";
                articleVo.setBanner("4");
                break;
            default:
                throw new BaseException("请求参数错误");
        }
        //文章分页列表
        articleVo.setPage(page);
        articleVo.setRows(articleRows);
        model.addAttribute("articlePageInfo", commonArticleService.findArticleListForHome(articleVo));
        //banner简拼
        model.addAttribute("bannerMark", bannerMark);
        //banner名称
        model.addAttribute("bannerName", bannerName);
        return "/home/guest/article/index";
    }

    /**
     * 返回首页的文章分页列表
     *
     * @param model
     * @param searchType 文章收索方式：key、label
     * @param param      文章查询参数：keyword、labelId
     * @param page       显示页码
     * @return 文章分页信息
     */
    @GetMapping("/list/{searchType}/{param}/page/{page}")
    public String findArticleListByPageOfIndex(Model model, @PathVariable String searchType, @PathVariable String param, @PathVariable Integer page) {
        //公共数据
        super.findCommonModule(model);
        //轮播
        model.addAttribute("carousel", commonArticleService.findArticleListByCarousel());
        //文章列表
        ArticleVo articleVo = new ArticleVo();
        articleVo.setPage(page);
        articleVo.setRows(articleRows);
        //查询参数
        switch (searchType) {
            case "key":
                articleVo.setKeyword(param);
                break;
            case "label":
                articleVo.setLabelId(param);
                break;
            case "empty":
                if (param != null && !param.equals("empty")) {
                    throw new BaseException("请求参数出错");
                }
                break;
            default:
                throw new BaseException("请求参数出错");
        }
        model.addAttribute("articlePageInfo", commonArticleService.findArticleListForHome(articleVo));
        model.addAttribute("searchType", searchType);
        model.addAttribute("param", param);
        return "/home/index";
    }

    /**
     * 根据文章编号查询文章详情（包括评论，标签）
     *
     * @param model
     * @param articleId 文章编号
     * @return 文章详情
     */
    @GetMapping("/detail/{articleId}")
    public String findArticleDetail(Model model, @PathVariable String articleId) {
        //公共数据
        super.findCommonModule(model);
        //文章详情
        ArticleVo articleDetail = commonArticleService.findArticleDetailwithLabelCommentByarticleId(articleId);
        String banner = articleDetail.getBanner();
        //banner标识
        String bannerMark;
        //banner名字
        String bannerName;
        switch (banner) {
            case "1":
                bannerMark = "sc";
                bannerName = "技术交流";
                break;
            case "2":
                bannerMark = "ll";
                bannerName = "生活日志";
                break;
            case "3":
                bannerMark = "hl";
                bannerName = "兴趣爱好";
                break;
            case "4":
                bannerMark = "a";
                bannerName = "关于";
                break;
            default:
                throw new BaseException("参数返回错误！");
        }
        //banner简拼
        model.addAttribute("bannerMark", bannerMark);
        //banner名称
        model.addAttribute("bannerName", bannerName);
        //文章详情
        model.addAttribute("articleDetail", articleDetail);
        return "/home/guest/article/_article_detail";
    }
}
