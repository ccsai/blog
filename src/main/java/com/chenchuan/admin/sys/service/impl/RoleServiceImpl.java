package com.chenchuan.admin.sys.service.impl;

import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.dao.RoleDao;
import com.chenchuan.admin.sys.po.RolePo;
import com.chenchuan.admin.sys.service.RoleService;
import com.chenchuan.admin.sys.vo.RoleVo;
import com.chenchuan.admin.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色service实现
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<RolePo> findRolesPermissionsByUser(UserVo userVo) {
        //获取对应用户的角色
        List<RolePo> roleList = roleDao.findRolesByUser(userVo);
        if (roleList == null && roleList.size() == 0) {
            return null;
        }
        //获取角色对应权限
        RoleVo roleVo = new RoleVo();
        for (RolePo r : roleList) {
            roleVo.setRoleId(r.getRoleId());
            r.setPermissionList(permissionDao.findPermissionsByRole(roleVo));
        }
        return roleList;
    }
}
