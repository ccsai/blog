package com.chenchuan.admin.sys.service.impl;

import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.po.PermissionPo;
import com.chenchuan.admin.sys.service.PermissionService;
import com.chenchuan.admin.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public List<PermissionPo> findPermissionsList(PermissionVo permissionVo) {
        return permissionDao.findPermissionsList(permissionVo);
    }
}
