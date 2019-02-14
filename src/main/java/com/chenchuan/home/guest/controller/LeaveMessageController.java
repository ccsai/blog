package com.chenchuan.home.guest.controller;

import com.chenchuan.admin.blog.service.LeaveMessageService;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 留言controller
 */
@Controller(value = "guestHomeLeaveMessageController")
@RequestMapping("/g.lm")
public class LeaveMessageController extends BaseController {

    @Autowired
    private LeaveMessageService leaveMessageService;


    /**
     * 根据用户展示留言
     *
     * @param model
     * @return 留言列表
     */
    @GetMapping("/list")
    public String findLeaveMessageByUserId(Model model) {
        //公共模块数据
        super.findCommonModule(model);
        //留言列表
        model.addAttribute("leaveMessageList", leaveMessageService.findLeaveMessageByUserForHome());
        return "/home/guest/leave_message/index";
    }
}
