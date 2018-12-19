package com.chenchuan.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 无权限controller
 */
@Controller
@RequestMapping("/common")
public class NoPermissionController extends BaseController {

    @GetMapping("/index")
    public String index() {
        return "/common/403";
    }
}
