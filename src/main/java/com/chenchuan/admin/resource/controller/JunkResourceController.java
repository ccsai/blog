package com.chenchuan.admin.resource.controller;

import com.chenchuan.admin.resource.service.JunkResouceService;
import com.chenchuan.admin.resource.service.OssService;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 垃圾数据资源处理
 */
@Controller
@RequestMapping("/admin/resource/junkResource")
public class JunkResourceController extends BaseController {

    @Autowired
    private JunkResouceService junkResouceService;

    @Autowired
    private OssService ossService;


    @GetMapping("/index")
    public String index() {
        return "/admin/resource/junk_resource/index";
    }

    /**
     * 安全垃圾数据清理
     *
     * @return 清理状态
     */
    @PostMapping("/removeSecurityJunkData")
    @ResponseBody
    public Map<String, Object> removeSecurityJunkData() {
        Map<String, Object> map = new HashMap<>();
        //清理
        map.put("resultCode", junkResouceService.removeSecurityJunkData());
        return map;
    }

    /**
     * 博客业务垃圾数据清理
     *
     * @return 清理状态
     */
    @PostMapping("/removeBlogBusiJunkData")
    @ResponseBody
    public Map<String, Object> removeBlogBusiJunkData() {
        Map<String, Object> map = new HashMap<>();
        //清理
        map.put("resultCode", junkResouceService.removeBlogBusiJunkData());
        return map;
    }

    /**
     * 从云上和数据库清楚垃圾oss
     *
     * @return 清清除状态
     */
    @PostMapping("/removeJunkOssFromOssAndDB")
    @ResponseBody
    public Map<String, Object> removeJunkOssFromOssAndDB() {
        Map<String, Object> map = new HashMap<>();
        //清楚
        map.put("resultCode", ossService.removeJunkFromOssAndDB());
        return map;
    }
}
