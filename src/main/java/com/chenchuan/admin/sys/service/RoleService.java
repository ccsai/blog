package com.chenchuan.admin.sys.service;

import com.chenchuan.admin.sys.po.RolePo;
import com.chenchuan.admin.sys.vo.RoleVo;
import com.chenchuan.admin.sys.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

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

    /**
     * 菜单授权
     *
     * @param roleMenuPermissionObj 具有要添加的角色与菜单权限的关系对象
     * @return 授权状态码
     */
    int addRoleMenuAuth(Map<String, Object> roleMenuPermissionObj);

    /**
     * 通用授权
     *
     * @param roleMenuPermissionObj 具有要添加的角色与菜单权限的关系对象
     * @return 授权状态码
     */
    int addRoleCommonPermissionAuth(Map<String, Object> roleMenuPermissionObj);

    /**
     * 根据用户编号查询用户角色关联的角色列表
     *
     * @param userId 用户编号
     * @return 具有用户角色关系的角色列表
     */
    List<RoleVo> findUserRoleAuthByUserId(String userId);
}
