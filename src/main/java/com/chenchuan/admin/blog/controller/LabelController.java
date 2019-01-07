package com.chenchuan.admin.blog.controller;

import com.chenchuan.admin.blog.service.LabelService;
import com.chenchuan.admin.blog.vo.LabelVo;
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
 * 标签controller
 */
@Controller
@RequestMapping("/admin/blog/label")
public class LabelController extends BaseController {

    @Autowired
    private LabelService labelService;


    @GetMapping("/index")
    public String index(){
        return "/admin/blog/label/index";
    }

    /**
     * 查询标签列表
     *
     * @param labelVo
     * @return 标签列表
     */
    @GetMapping("/findLabelList")
    @ResponseBody
    public Map<String, Object> findLabelList(LabelVo labelVo) {
        Map<String, Object> map = new HashMap<>();
        //标签列表
        map.put("labelList", labelService.findLabelList(labelVo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 添加标签
     *
     * @param labelVo
     * @return 添加状态
     */
    @PostMapping("/addLabel")
    @ResponseBody
    public Map<String, Object> addLabel(LabelVo labelVo) {
        Map<String, Object> map = new HashMap<>();
        //标签添加
        map.put("resultCode", labelService.addLabel(labelVo));
        return map;
    }

    /**
     * 根据编号查询标签
     *
     * @param labelId 标签编号
     * @return 标签详情
     */
    @GetMapping("/findLabelByLabelId")
    @ResponseBody
    public Map<String, Object> findLabelByLabelId(String labelId) {
        Map<String, Object> map = new HashMap<>();
        //标签详情
        map.put("labelDetail", labelService.findLabelByLabelId(labelId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据编号修改标签
     *
     * @param labelVo
     * @return 修改状态
     */
    @PostMapping("/editLabelByLabelId")
    @ResponseBody
    public Map<String, Object> editLabelByLabelId(LabelVo labelVo) {
        Map<String, Object> map = new HashMap<>();
        //标签修改
        map.put("resultCode", labelService.editLabelByLabelId(labelVo));
        return map;
    }

    /**
     * 根据编号删除标签
     *
     * @param labelId 标签编号
     * @return 修改状态
     */
    @PostMapping("/removeLabelByLabelId")
    @ResponseBody
    public Map<String, Object> removeLabelByLabelId(String labelId) {
        Map<String, Object> map = new HashMap<>();
        //标签删除
        map.put("resultCode", labelService.removeLabelByLabelId(labelId));
        return map;
    }
}
