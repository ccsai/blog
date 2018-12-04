package com.chenchuan.admin.sys.service;

import com.chenchuan.admin.sys.po.PermissionPo;
import com.chenchuan.admin.sys.vo.PermissionVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 权限service接口
 */
public interface PermissionService {

    /**
     * 分页查询权限列表
     *
     * @param permissionVo
     * @return 权限分页信息
     */
    PageInfo<PermissionVo> findPermissionsListByPage(PermissionVo permissionVo);

    /**
     * 添加权限
     *
     * @param permissionVo
     * @return 添加结果状态
     */
    int addPermission(PermissionVo permissionVo);

    /**
     * 根据权限编号查看权限详情
     *
     * @param permissionId 权限编号
     * @return 权限详情
     */
    PermissionPo findPermissionByPermissionId(String permissionId);

    /**
     * 根据权限编号修改权限
     *
     * @param permissionVo
     * @return 修改结果状态
     */
    int editPermissionByPermissionId(PermissionVo permissionVo);

    /**
     * 根据权限编号删除权限
     *
     * @param permissionId 权限编号
     * @return 删除返回状态
     */
    int removePermissionByPermissionId(String permissionId);

    /**
     * 通过角色编号查询角色与通用权限关联的权限列表
     *
     * @param roleId 角色编号
     * @return 权限集合
     */
    List<PermissionVo> findRoleCommonPermissionAuthListByRole(String roleId);
}
