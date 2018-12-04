package com.chenchuan.admin.sys.controller;

import com.chenchuan.admin.sys.service.MenuService;
import com.chenchuan.admin.sys.service.PermissionService;
import com.chenchuan.admin.sys.service.RoleService;
import com.chenchuan.admin.sys.vo.RoleVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色controller
 */
@Controller
@RequestMapping("/admin/sys/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PermissionService permissionService;


    @GetMapping("/index")
    public String index() {
        return "/admin/sys/role/index";
    }

    /**
     * 分页查询角色列表
     *
     * @param roleVo
     * @return 角色分页信息
     */
    @GetMapping("/findRoleListByPage")
    @ResponseBody
    public Map<String, Object> findRoleListByPage(RoleVo roleVo) {
        Map<String, Object> map = new HashMap<>();
        //分页查询
        map.put("rolePageInfo", roleService.findRoleListByPage(roleVo));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 添加角色
     *
     * @param roleVo
     * @return 添加结果状态
     */
    @PostMapping("/addRole")
    @ResponseBody
    public Map<String, Object> addRole(RoleVo roleVo) {
        Map<String, Object> map = new HashMap<>();
        //添加角色
        map.put("resultCode", roleService.addRole(roleVo));
        return map;
    }

    /**
     * 根据角色编号查询角色详情
     *
     * @param roleId 角色编号
     * @return 角色详情
     */
    @GetMapping("/findRoleByRoleId")
    @ResponseBody
    public Map<String, Object> findRoleByRoleId(String roleId) {
        Map<String, Object> map = new HashMap<>();
        //角色详情
        map.put("roleDetail", roleService.findRoleByRoleId(roleId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据角色编号修改角色
     *
     * @param roleVo
     * @return 修改结果状态
     */
    @PostMapping("/editRoleByRoleId")
    @ResponseBody
    public Map<String, Object> editRoleByRoleId(RoleVo roleVo) {
        Map<String, Object> map = new HashMap<>();
        //修改角色
        map.put("resultCode", roleService.editRoleByRoleId(roleVo));
        return map;
    }

    /**
     * 根据角色编号删除角色
     *
     * @param roleId 角色编号
     * @return 删除结果状态
     */
    @PostMapping("/removeRoleByRoleId")
    @ResponseBody
    public Map<String, Object> removeRoleByRoleId(String roleId) {
        Map<String, Object> map = new HashMap<>();
        //删除角色
        map.put("resultCode", roleService.removeRoleByRoleId(roleId));
        return map;
    }

    /**
     * 根据角色编号展示与菜单权限的关系（菜单树形的数据）
     *
     * @param roleId 角色编号
     * @return 菜单集合
     */
    @GetMapping("/findRoleMenuPermissionAuthTree")
    @ResponseBody
    public Map<String, Object> findRoleMenuPermissionAuthTree(String roleId) {
        Map<String, Object> map = new HashMap<>();
        //根据角色编号展示与菜单权限的关系（菜单树形的数据）
        map.put("menuTree", menuService.findRoleMenuPermissionAuthTree(roleId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 菜单授权
     *
     * @param formData 具有要添加的角色与菜单权限的关系对象
     * @return 授权状态码
     */
    @PostMapping("/authorizationMenuPermission")
    @ResponseBody
    public Map<String, Object> authorizationMenuPermission(@RequestParam Map<String, Object> formData) {
        Map<String, Object> map = new HashMap<>();
        //菜单授权
        map.put("resultCode", roleService.addRoleMenuAuth(formData));
        return map;
    }

    /**
     * 通过角色编号查询角色与通用权限关联的权限列表
     *
     * @param roleId 角色编号
     * @return 权限集合
     */
    @GetMapping("/findRoleCommonPermissionAuthListByRole")
    @ResponseBody
    public Map<String, Object> findRoleCommonPermissionAuthListByRole(String roleId) {
        Map<String, Object> map = new HashMap<>();
        //获取角色与通用权限关联的权限列表
        map.put("permissionList", permissionService.findRoleCommonPermissionAuthListByRole(roleId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 通用权限配置
     *
     * @param roleIdPermissionIds 角色编号与权限编号对应关系对象
     * @return 操作结果状态
     */
    @PostMapping("/addRoleCommonPermissionAuth")
    @ResponseBody
    public Map<String, Object> addRoleCommonPermissionAuth(@RequestParam Map<String, Object> roleIdPermissionIds) {
        Map<String, Object> map = new HashMap<>();
        //添加角色权限关联
        map.put("resultCode", roleService.addRoleCommonPermissionAuth(roleIdPermissionIds));
        return map;
    }
}