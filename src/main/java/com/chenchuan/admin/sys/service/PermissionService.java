package com.chenchuan.admin.sys.service;

import com.chenchuan.admin.sys.po.PermissionPo;
import com.chenchuan.admin.sys.vo.PermissionVo;

import java.util.List;

/**
 * 权限service接口
 */
public interface PermissionService {

    /**
     * 查询权限列表
     *
     * @param permissionVo
     * @return 权限集合
     */
    List<PermissionPo> findPermissionsList(PermissionVo permissionVo);
}
