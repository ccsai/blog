package com.chenchuan.admin.sys.controller;

import com.chenchuan.admin.sys.service.RoleService;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户controller
 */
@Controller
@RequestMapping("/admin/sys/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/index")
    public String index() {
        return "/admin/sys/user/index";
    }

    /**
     * 用户分页列表
     *
     * @param userVo
     * @return 用户分页信息
     */
    @GetMapping("/findUserListByPage")
    @ResponseBody
    public Map<String, Object> findUserListByPage(UserVo userVo) {
        Map<String, Object> map = new HashMap<>();
        //分用户页
        map.put("userPageInfo", userService.findUserListByPage(userVo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 添加用户
     *
     * @param userVo
     * @return 添加结果状态
     */
    @PostMapping("/addUser")
    @ResponseBody
    public Map<String, Object> addUser(UserVo userVo) {
        Map<String, Object> map = new HashMap<>();
        //添加户页
        map.put("resultCode", userService.addUser(userVo));
        return map;
    }

    /**
     * 根据用户编号查询用户信息
     *
     * @param userId 用户编号
     * @return 用户详情
     */
    @GetMapping("/findUserByUserId")
    @ResponseBody
    public Map<String, Object> findUserByUserId(String userId) {
        Map<String, Object> map = new HashMap<>();
        //用户详情
        map.put("userDetail", userService.findUserByUserId(userId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据用户编号修改用户
     *
     * @param userVo
     * @return 修改结果状态
     */
    @PostMapping("/editUserByUserId")
    @ResponseBody
    public Map<String, Object> editUserByUserId(UserVo userVo) {
        Map<String, Object> map = new HashMap<>();
        //修改用户
        map.put("resultCode", userService.editUserByUserId(userVo));
        return map;
    }

    /**
     * 根据用户编号删除用户
     *
     * @param userId 用户编号
     * @return 删除结果状态
     */
    @PostMapping("/removeUserByUserId")
    @ResponseBody
    public Map<String, Object> removeUserByUserId(String userId) {
        Map<String, Object> map = new HashMap<>();
        //修改用户
        map.put("resultCode", userService.removeUserByUserId(userId));
        return map;
    }

    /**
     * 根据用户编号查询用户角色关联的角色列表
     *
     * @param userId 用户编号
     * @return 用户角色关联的角色列表
     */
    @GetMapping("/findUserRoleAuthListByUserId")
    @ResponseBody
    public Map<String, Object> findUserRoleAuthListByUserId(String userId) {
        Map<String, Object> map = new HashMap<>();
        //用户角色关联的角色列表
        map.put("roleList", roleService.findUserRoleAuthByUserId(userId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 添加用户角色关联
     *
     * @param userIdRoleIdsAuthObj 用户编号角色编号关联对象
     * @return 添加结果状态
     */
    @PostMapping("/addUserRoleAuth")
    @ResponseBody
    public Map<String, Object> addUserRoleAuth(@RequestParam Map<String, Object> userIdRoleIdsAuthObj) {
        Map<String, Object> map = new HashMap<>();
        //用户角色关联的角色列表
        map.put("resultCode", userService.addUserRoleAuth(userIdRoleIdsAuthObj));
        return map;
    }
}