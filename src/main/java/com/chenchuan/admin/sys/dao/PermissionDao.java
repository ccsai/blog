package com.chenchuan.admin.sys.dao;

import com.chenchuan.admin.sys.po.PermissionPo;
import com.chenchuan.admin.sys.vo.PermissionVo;
import com.chenchuan.admin.sys.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统权限dao
 */
@Mapper
@Repository
public interface PermissionDao {

    /**
     * 查询所有权限（用于配置）
     *
     * @return 权限集合
     */
    List<PermissionPo> findAllPermissions();

    /**
     * 查询权限列表
     *
     * @param permissionVo
     * @return 权限集合
     */
    List<PermissionVo> findPermissionsList(PermissionVo permissionVo);

    /**
     * 通过角色查询对应权限
     *
     * @param roleVo
     * @return 角色对应的权限集合
     */
    List<PermissionPo> findPermissionsByRole(RoleVo roleVo);

    /**
     * 根据菜单查询对应菜单权限数量
     *
     * @param menuId 菜单编号
     * @return 对应菜单相关权限数量
     */
    int findPermissionNumberByMemu(String menuId);

    /**
     * 添加权限
     *
     * @param permissionVo
     * @return 影响行数
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
     * @return 影响行数
     */
    int editPermissionByPermissionId(PermissionVo permissionVo);

    /**
     * 根据权限编号删除权限
     *
     * @param permissionId 权限编号
     * @return 影响行数
     */
    int removePermissionByPermissionId(String permissionId);

    /**
     * 根据权限编号删除角色和权限关联表
     *
     * @param permissionId 权限编号
     * @return 影响行数
     */
    int removeRolePermissionAuthByPermissionId(String permissionId);

    /**
     * 通过角色编号查询角色与通用权限关联的权限列表
     *
     * @param roleId 角色编号
     * @return 权限集合
     */
    List<PermissionVo> findRoleCommonPermissionAuthListByRole(String roleId);
}
