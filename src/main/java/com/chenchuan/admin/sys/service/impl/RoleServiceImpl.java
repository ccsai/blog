package com.chenchuan.admin.sys.service.impl;

import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.dao.RoleDao;
import com.chenchuan.admin.sys.po.RolePo;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.RoleService;
import com.chenchuan.admin.sys.vo.RoleVo;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public PageInfo<RolePo> findRoleListByPage(RoleVo roleVo) {
        PageHelper.startPage(roleVo.getPage(), roleVo.getRows());
        //角色列表
        List<RolePo> roleList = roleDao.findRoleList(roleVo);
        return new PageInfo<>(roleList);
    }

    @Override
    @Transactional
    public int addRole(RoleVo roleVo) {
        roleVo.setRoleId(UuidUtil.getUuid());//主键
        //当前登录用户
        String loginName = ((UserPo) SecurityUtils.getSubject().getPrincipal()).getLoginName();
        roleVo.setCreateUser(loginName);
        roleVo.setModifyUser(loginName);
        //添加角色
        int result = roleDao.addRole(roleVo);
        if (result == 0) {
            throw new BaseException("未添加角色，请稍后重试或联系管理员！");
        }
        return result;
    }

    @Override
    public RolePo findRoleByRoleId(String roleId) {
        //角色详情
        RolePo role = roleDao.findRoleByRoleId(roleId);
        if (role == null) {
            throw new BaseException("角色可能被删除，请刷新表格查看。如确认存在，请联系管理员！");
        }
        return role;
    }

    @Override
    @Transactional
    public int editRoleByRoleId(RoleVo roleVo) {
        roleVo.setModifyUser(((UserPo) SecurityUtils.getSubject().getPrincipal()).getLoginName());
        //修改角色
        int result = roleDao.editRoleByRoleId(roleVo);
        if (result == 0) {
            throw new BaseException("未修改菜单，请稍后重试或联系管理员！");
        }
        return result;
    }

    @Override
    @Transactional
    public int removeRoleByRoleId(String roleId) {
        //删除角色
        int result = roleDao.removeRoleByRoleId(roleId);
        if (result == 0) {
            throw new BaseException("未删除角色，请稍后重试或联系管理员！");
        }
        //删除角色权限关联
        roleDao.removeRolePermissionAuthByRoleId(roleId);
        //根据角色编号删除角色菜单关联
        roleDao.removeRoleMenuAuthByRoleId(roleId);
        //根据角色编号删除用户角色关联
        roleDao.removeUserRoleAuthByRoleId(roleId);
        return result;
    }
}
