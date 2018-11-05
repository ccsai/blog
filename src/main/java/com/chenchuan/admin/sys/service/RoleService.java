package com.chenchuan.admin.sys.service;

import com.chenchuan.admin.sys.po.RolePo;
import com.chenchuan.admin.sys.vo.UserVo;

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
}
