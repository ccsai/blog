package com.chenchuan.admin.blog.controller;

import com.chenchuan.admin.blog.service.LeaveMessageService;
import com.chenchuan.admin.blog.vo.LeaveMessageVo;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 留言controller
 */
@Controller
@RequestMapping("/admin/blog/leaveMessage")
public class LeaveMessageController extends BaseController {

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Autowired
    private UserService userService;


    @GetMapping("/index")
    public String index() {
        return "/admin/blog/leave_message/index";
    }

    /**
     * 管理员查询所有用户以及是否有新留言
     *
     * @return 用户列表及留言未读数量
     */
    @GetMapping("/findIsHaveNewLeaveMessageByManager")
    @ResponseBody
    public Map<String, Object> findIsHaveNewLeaveMessageByManager(UserVo userVo) {
        Map<String, Object> map = new HashMap<>();
        //获取用户
        map.put("userList", userService.findIsHaveNewLeaveMessageByManager(userVo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据接收留言的用户编号查询自己发送和收到的留言
     *
     * @param targetSendUserAndIsReadInfo 用户编号与留言已读信息targetUser、sendUser
     * @return 用户相关留言集合
     */
    @GetMapping("/findLeaveMessageByUserId")
    @ResponseBody
    public Map<String, Object> findLeaveMessageByUserId(@RequestParam Map<String, Object> targetSendUserAndIsReadInfo) {
        Map<String, Object> map = new HashMap<>();
        //获取用户
        map.put("leaveMessageList", leaveMessageService.findLeaveMessageByUserId(targetSendUserAndIsReadInfo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 发送留言
     *
     * @param leaveMessageVo
     * @param ossKeys        oss keys
     * @return 发送状态
     */
    @PostMapping("/leaveMessage")
    @ResponseBody
    public Map<String, Object> leaveMessage(LeaveMessageVo leaveMessageVo, @RequestParam(value = "ossKeys[]", required = false) String[] ossKeys) {
        Map<String, Object> map = new HashMap<>();
        //获取用户
        map.put("resultCode", leaveMessageService.addLeaveMessage(leaveMessageVo, ossKeys));
        return map;
    }

    /**
     * 根据编号批量删除留言
     *
     * @param leaveMessageIds 留言编号字符串
     * @return 删除啊状态
     */
    @PostMapping("/removeLeaveMessagesByLeaveMessageIds")
    @ResponseBody
    public Map<String, Object> removeLeaveMessagesByLeaveMessageIds(String leaveMessageIds) {
        Map<String, Object> map = new HashMap<>();
        //获取用户
        map.put("resultCode", leaveMessageService.removeLeaveMessagesByLeaveMessageIds(leaveMessageIds));
        return map;
    }
}
