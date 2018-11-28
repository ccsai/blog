package com.chenchuan.admin.sys.service;

import com.chenchuan.admin.sys.po.RolePo;
import com.chenchuan.admin.sys.vo.RoleVo;
import com.chenchuan.admin.sys.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 角色service接口
 */
public interface RoleService {

    /**
     * 根据用户查找对应角色和权限
     *
     * @param userVo
     * @return 角色权限有关系的角色集合
     */
    List<RolePo> findRolesPermissionsByUser(UserVo userVo);

    /**
     * 分页查询所有角色列表
     *
     * @param roleVo
     * @return 角色列表
     */
    PageInfo<RolePo> findRoleListByPage(RoleVo roleVo);

    /**
     * 添加角色
     *
     * @param roleVo
     * @return 调节价结果状态
     */
    int addRole(RoleVo roleVo);

    /**
     * 根据角色编号查询角色详情
     *
     * @param roleId 角色编号
     * @return 角色详情
     */
    RolePo findRoleByRoleId(String roleId);

    /**
     * 根据角色编号修改角色
     *
     * @param roleVo
     * @return 修改结果状态
     */
    int editRoleByRoleId(RoleVo roleVo);

    /**
     * 根据角色编号删除角色
     *
     * @param roleId 角色编号
     * @return 删除结果状态
     */
    int removeRoleByRoleId(String roleId);
}
