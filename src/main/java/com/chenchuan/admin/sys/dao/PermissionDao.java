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
     * 查询权限列表
     *
     * @param permissionVo
     * @return 权限集合
     */
    List<PermissionPo> findPermissionsList(PermissionVo permissionVo);

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
}
