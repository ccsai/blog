package com.chenchuan.admin.sys.controller;

import com.chenchuan.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/userList")
    @ResponseBody
    public String userList() {
        return "用户列表";
    }

    @PostMapping("/addUser")
    @ResponseBody
    public void addUser() {
        System.out.println("添加用户");
    }

    @GetMapping("/findUserByUserId")
    @ResponseBody
    public void findUserByUserId() {
        System.out.println("用户详情");
    }

    @PostMapping("/editUserByUserId")
    @ResponseBody
    public void editUserByUserId() {
        System.out.println("编辑用户");
    }

    @PostMapping("/removeUserByUserId")
    @ResponseBody
    public void removeUserByUserId() {
        System.out.println("删除用户");
    }
}
