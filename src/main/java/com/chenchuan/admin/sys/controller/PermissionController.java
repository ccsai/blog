package com.chenchuan.admin.sys.controller;

import com.chenchuan.admin.sys.service.PermissionService;
import com.chenchuan.admin.sys.vo.PermissionVo;
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
 * 权限controller
 */
@Controller
@RequestMapping("/admin/sys/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/index")
    public String index() {
        return "/admin/sys/permission/index";
    }

    /**
     * 分页查询权限列表
     *
     * @param permissionVo
     * @return 权限列表分页信息
     */
    @GetMapping("/findPermissionsListByPage")
    @ResponseBody
    public Map<String, Object> findPermissionsListByPage(PermissionVo permissionVo) {
        Map<String, Object> map = new HashMap<>();
        //权限列表
        map.put("permissionPageInfo", permissionService.findPermissionsListByPage(permissionVo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 添加权限
     *
     * @param permissionVo
     * @return 添加结果状态
     */
    @PostMapping("/addPermission")
    @ResponseBody
    public Map<String, Object> addPermission(PermissionVo permissionVo) {
        Map<String, Object> map = new HashMap<>();
        //添加
        map.put("resultCode", permissionService.addPermission(permissionVo));
        return map;
    }

    /**
     * 根据权限编号查询权限
     *
     * @param permissionId 权限编号
     * @return 权限详情
     */
    @GetMapping("/findPermissionByPermissionId")
    @ResponseBody
    public Map<String, Object> findPermissionByPermissionId(String permissionId) {
        Map<String, Object> map = new HashMap<>();
        //权限详情
        map.put("permissionDetail", permissionService.findPermissionByPermissionId(permissionId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据权限编号修改权限
     *
     * @param permissionVo
     * @return 修改结果状态
     */
    @PostMapping("/editPermissionByPermissionId")
    @ResponseBody
    public Map<String, Object> editPermissionByPermissionId(PermissionVo permissionVo) {
        Map<String, Object> map = new HashMap<>();
        //修改权限
        map.put("resultCode", permissionService.editPermissionByPermissionId(permissionVo));
        return map;
    }

    /**
     * 根据权限编号删除权限
     *
     * @param permissionId 权限编号
     * @return 删除结果状态
     */
    @PostMapping("/removePermissionByPermissionId")
    @ResponseBody
    public Map<String, Object> removePermissionByPermissionId(String permissionId) {
        Map<String, Object> map = new HashMap<>();
        //修改权限
        map.put("resultCode", permissionService.removePermissionByPermissionId(permissionId));
        return map;
    }
}
