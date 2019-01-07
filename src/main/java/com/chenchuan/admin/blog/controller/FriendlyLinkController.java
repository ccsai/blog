package com.chenchuan.admin.blog.controller;

import com.chenchuan.admin.blog.service.FriendlyLinkServie;
import com.chenchuan.admin.blog.vo.FriendlyLinkVo;
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
 * 友情链接controller
 */
@Controller
@RequestMapping("/admin/blog/friendlyLink")
public class FriendlyLinkController extends BaseController {

    @Autowired
    private FriendlyLinkServie friendlyLinkServie;


    @GetMapping("/index")
    public String index() {
        return "/admin/blog/friendly_link/index";
    }

    /**
     * 查询友链列表
     *
     * @return 友链列表
     */
    @GetMapping("/findFriendlyLinkList")
    @ResponseBody
    public Map<String, Object> findFriendlyLinkList(FriendlyLinkVo friendlyLinkVo) {
        Map<String, Object> map = new HashMap<>();
        //查询友链列表
        map.put("friendlyLinkList", friendlyLinkServie.findFriendlyLinkList(friendlyLinkVo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 添加友链
     *
     * @param friendlyLinkVo
     * @return 添加状态
     */
    @PostMapping("/addFriendlyLink")
    @ResponseBody
    public Map<String, Object> addFriendlyLink(FriendlyLinkVo friendlyLinkVo) {
        Map<String, Object> map = new HashMap<>();
        //添加友链
        map.put("resultCode", friendlyLinkServie.addFriendlyLink(friendlyLinkVo));
        return map;
    }

    /**
     * 根据编号查询友链详情
     *
     * @param friendlyLinkId 友链编号
     * @return 友链详情
     */
    @GetMapping("/findFriendlyLinkByFriendlyLinkId")
    @ResponseBody
    public Map<String, Object> findFriendlyLinkByFriendlyLinkId(String friendlyLinkId) {
        Map<String, Object> map = new HashMap<>();
        //友链详情
        map.put("friendlyLinkDetail", friendlyLinkServie.findFriendlyLinkByFriendlyLinkId(friendlyLinkId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据编号修改友链
     *
     * @param friendlyLinkVo
     * @return 修改状态
     */
    @PostMapping("/editFriendlyLinkByFriendlyLinkId")
    @ResponseBody
    public Map<String, Object> editFriendlyLinkByFriendlyLinkId(FriendlyLinkVo friendlyLinkVo) {
        Map<String, Object> map = new HashMap<>();
        //友链修改
        map.put("resultCode", friendlyLinkServie.editFriendlyLinkByFriendlyLinkId(friendlyLinkVo));
        return map;
    }

    /**
     * 根据编号删除友链
     *
     * @param friendlyLinkId 友链编号
     * @return 删除状态
     */
    @PostMapping("/removeFriendlyLinkByFriendlyLinkId")
    @ResponseBody
    public Map<String, Object> removeFriendlyLinkByFriendlyLinkId(String friendlyLinkId) {
        Map<String, Object> map = new HashMap<>();
        //友链删除
        map.put("resultCode", friendlyLinkServie.removeFriendlyLinkByFriendlyLinkId(friendlyLinkId));
        return map;
    }
}
