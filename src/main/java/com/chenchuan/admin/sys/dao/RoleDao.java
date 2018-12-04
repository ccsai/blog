package com.chenchuan.admin.sys.dao;

import com.chenchuan.admin.sys.po.RolePo;
import com.chenchuan.admin.sys.vo.RoleVo;
import com.chenchuan.admin.sys.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色dao
 */
@Mapper
@Repository
public interface RoleDao {


    /**
     * 根据用户查询角色
     *
     * @param userVo
     * @return 当前用户角色集合
     */
    List<RolePo> findRolesByUser(UserVo userVo);

    /**
     * 查询所有角色列表
     *
     * @param roleVo
     * @return 角色列表
     */
    List<RolePo> findRoleList(RoleVo roleVo);

    /**
     * 添加角色
     *
     * @param roleVo
     * @return 影响行数
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
     * @return 影响行数
     */
    int editRoleByRoleId(RoleVo roleVo);

    /**
     * 根据角色编号删除角色
     *
     * @param roleId 角色编号
     * @return 影响行数
     */
    int removeRoleByRoleId(String roleId);

    /**
     * 根据角色编号删除角色权限关联
     *
     * @param roleId 角色编号
     * @return 影响行数
     */
    int removeRolePermissionAuthByRoleId(String roleId);

    /**
     * 根据角色编号删除角色菜单关联
     *
     * @param roleId 角色编号
     * @return 影响行数
     */
    int removeRoleMenuAuthByRoleId(String roleId);

    /**
     * 根据角色编号删除用户角色关联
     *
     * @param roleId 角色编号
     * @return 影响行数
     */
    int removeUserRoleAuthByRoleId(String roleId);

    /**
     * 授权部分：根据角色编号删除角色权限关联
     *
     * @param roleIdAuthType 角色编号和授权类型
     * @return 影响行数
     */
    int removeRolePermissionAuthByRoleIdOfAuth(Map<String, String> roleIdAuthType);

    /**
     * 添加角色菜单关联
     *
     * @param roleIdMemuIdAuthObj 角色菜单关系集合
     * @return 影响行数
     */
    int addRoleMenuAuth(Map<String, Object> roleIdMemuIdAuthObj);

    /**
     * 添加角色权限关联
     *
     * @param roleIdPermissionIdAuthObj 角色权限关系集合
     * @return 影响行数
     */
    int addRolePermissionAuth(Map<String, Object> roleIdPermissionIdAuthObj);

}
