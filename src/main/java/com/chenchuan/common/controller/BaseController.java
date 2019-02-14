package com.chenchuan.common.controller;

import com.chenchuan.admin.blog.service.ArticleService;
import com.chenchuan.admin.blog.service.FriendlyLinkServie;
import com.chenchuan.admin.blog.service.LabelService;
import com.chenchuan.admin.blog.vo.FriendlyLinkVo;
import com.chenchuan.admin.blog.vo.LabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共Controller
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected LabelService commonLabelService;

    @Autowired
    protected FriendlyLinkServie commonFriendlyLinkServie;

    @Autowired
    protected ArticleService commonArticleService;


    /**
     * 获取http请求对象
     *
     * @return http请求对象
     */
    protected HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 查询前台公共模块数据
     *
     * @param model
     * @return 公共模块
     */
    protected Model findCommonModule(Model model) {
        //标签列表
        model.addAttribute("labelList", commonLabelService.findLabelList(new LabelVo()));
        //友情链接列表
        FriendlyLinkVo friendlyLinkVo = new FriendlyLinkVo();
        friendlyLinkVo.setIsShow(1);
        model.addAttribute("friendlyLinkList", commonFriendlyLinkServie.findFriendlyLinkList(friendlyLinkVo));
        //文章访问量排名
        model.addAttribute("rankingArticleList", commonArticleService.articleViewRanking());
        return model;
    }
}
