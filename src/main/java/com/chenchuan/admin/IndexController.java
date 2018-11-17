package com.chenchuan.admin;

import com.chenchuan.admin.index.service.IndexService;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 后台首页
 */
@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;

    /**
     * 进入后台首页
     *
     * @return 后台首页路径
     */
    @GetMapping("/index")
    public String index() {
        return "/admin/index";
    }

    /**
     * 加载首页组件和数据
     *
     * @return 页面所需组件
     */
    @GetMapping("/loadIndexPageData")
    @ResponseBody
    public Map<String, Object> loadIndexPageData() {
        //页面所需组件和数据
        Map<String, Object> map = indexService.loadIndexPageData();
        map.put("resultCode", 1);
        return map;
    }
}
