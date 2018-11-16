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

    @GetMapping("/findRoleList")
    @ResponseBody
    public void findRoleList() {
        System.out.println("角色列表");
    }

    @PostMapping("/addRole")
    @ResponseBody
    public void addRole() {
        System.out.println("角色添加");
    }

    @GetMapping("/findRoleByRoleVo")
    @ResponseBody
    public void findRoleByRoleVo() {
        System.out.println("角色详情");
    }

    @PostMapping("/editRoleByRoleVo")
    @ResponseBody
    public void editRoleByRoleVo() {
        System.out.println("角色修改");
    }

    @PostMapping("/removeRoleByRoleVo")
    @ResponseBody
    public void removeRoleByRoleVo() {
        System.out.println("角色删除");
    }
}
