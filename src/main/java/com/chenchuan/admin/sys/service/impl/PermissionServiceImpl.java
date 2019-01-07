package com.chenchuan.admin.sys.service.impl;

import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.po.PermissionPo;
import com.chenchuan.admin.sys.service.PermissionService;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.PermissionVo;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限service实现
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserService userService;


    @Override
    public PageInfo<PermissionVo> findPermissionsListByPage(PermissionVo permissionVo) {
        PageHelper.startPage(permissionVo.getPage(), permissionVo.getRows());
        //权限列表
        List<PermissionVo> permissionList = permissionDao.findPermissionsList(permissionVo);
        return new PageInfo<>(permissionList);
    }

    @Override
    @Transactional
    public int addPermission(PermissionVo permissionVo) {
        //主键
        permissionVo.setPermissionId(UuidUtil.getUuid());//当前登录用户名
        String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
        permissionVo.setCreateUser(userId);
        permissionVo.setModifyUser(userId);
        //添加权限
        int result = permissionDao.addPermission(permissionVo);
        if (result == 0) {
            throw new BaseException("未添加权限，请稍后重试或联系管理员！");
        }
        return result;
    }

    @Override
    public PermissionPo findPermissionByPermissionId(String permissionId) {
        return permissionDao.findPermissionByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public int editPermissionByPermissionId(PermissionVo permissionVo) {
        permissionVo.setModifyUser(userService.getCurrentLoginUserBaseInfo().getUserId());
        //修改权限
        int result = permissionDao.editPermissionByPermissionId(permissionVo);
        if (result == 0) {
            throw new BaseException("未修改权限，请稍后重试或联系管理员！");
        }
        return result;
    }

    @Override
    @Transactional
    public int removePermissionByPermissionId(String permissionId) {
        //删除权限
        int result = permissionDao.removePermissionByPermissionId(permissionId);
        if (result == 0) {
            throw new BaseException("未删除权限！");
        }
        //删除角色权限关联
        permissionDao.removeRolePermissionAuthByPermissionId(permissionId);
        return result;
    }

    @Override
    public List<PermissionVo> findRoleCommonPermissionAuthListByRole(String roleId) {
        return permissionDao.findRoleCommonPermissionAuthListByRole(roleId);
    }
}
