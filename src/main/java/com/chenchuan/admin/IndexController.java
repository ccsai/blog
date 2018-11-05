package com.chenchuan.admin;

import com.chenchuan.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页
 */
@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {

    /**
     * 进入后台首页
     *
     * @return 后台首页路径
     */
    @GetMapping("/index")
    public String index() {
        return "/admin/index";
    }
}
