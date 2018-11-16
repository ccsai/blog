package com.chenchuan.admin.sys.controller;

import com.chenchuan.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统用户controller
 */
@Controller
@RequestMapping("/admin/sys/user")
public class UserController extends BaseController {

    @GetMapping("/index")
    @ResponseBody
    public void index() {
        System.out.println("用户页面");
    }

    @GetMapping("/findUserList")
    @ResponseBody
    public String findUserList() {
        return "用户列表";
    }

    @PostMapping("/addUser")
    @ResponseBody
    public void addUser() {
        System.out.println("添加用户");
    }

    @GetMapping("/findUserByUserVo")
    @ResponseBody
    public void findUserByUserVo() {
        System.out.println("用户详情");
    }

    @PostMapping("/editUserByUserVo")
    @ResponseBody
    public void editUserByUserVo() {
        System.out.println("编辑用户");
    }

    @PostMapping("/removeUserByUserVo")
    @ResponseBody
    public void removeUserByUserVo() {
        System.out.println("删除用户");
    }
}
