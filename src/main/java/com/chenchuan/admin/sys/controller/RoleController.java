package com.chenchuan.admin.sys.controller;

import com.chenchuan.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色controller
 */
@Controller
@RequestMapping("/admin/sys/role")
public class RoleController extends BaseController {

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "角色首页";
    }

    @GetMapping("/roleList")
    @ResponseBody
    public void roleList() {
        System.out.println("角色列表");
    }

    @PostMapping("/addRole")
    @ResponseBody
    public void addRole() {
        System.out.println("角色添加");
    }

    @GetMapping("/findRoleByRoleId")
    @ResponseBody
    public void findRoleByRoleId() {
        System.out.println("角色详情");
    }

    @PostMapping("/editRoleByRoleId")
    @ResponseBody
    public void editRoleByRoleId() {
        System.out.println("角色修改");
    }

    @PostMapping("/removeRoleByRoleId")
    @ResponseBody
    public void removeRoleByRoleId() {
        System.out.println("角色删除");
    }
}
