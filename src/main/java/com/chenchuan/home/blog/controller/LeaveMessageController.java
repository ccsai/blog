package com.chenchuan.home.blog.controller;

import com.chenchuan.admin.blog.service.LeaveMessageService;
import com.chenchuan.admin.blog.vo.LeaveMessageVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 留言controller
 */
@Controller(value = "HomeBlogLeaveMessageController")
@RequestMapping("/b.lm")
public class LeaveMessageController extends BaseController {

    @Autowired
    private LeaveMessageService leaveMessageService;


    /**
     * 前台用户留言
     *
     * @param leaveMessageVo
     * @param ossKeys        oss keys
     * @return 留言状态
     */
    @PostMapping("/leaveMessage")
    @ResponseBody
    public Map<String, Object> leaveMessage(LeaveMessageVo leaveMessageVo, @RequestParam(value = "ossKeys[]", required = false) String[] ossKeys) {
        Map<String, Object> map = new HashMap<>();
        map.put("resultCode", leaveMessageService.addLeaveMessageByHome(leaveMessageVo, ossKeys));
        return map;
    }
}
