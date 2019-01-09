package com.chenchuan.admin.resource.service.impl;

import com.chenchuan.admin.resource.dao.JunkResouceDao;
import com.chenchuan.admin.resource.service.JunkResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 垃圾数据清理service实现
 */
@Service
public class JunkResouceServiceImpl implements JunkResouceService {

    @Autowired
    private JunkResouceDao junkResouceDao;


    @Override
    public int removeSecurityJunkData() {
        junkResouceDao.removeSecurityJunkData();
        return 1;
    }

    @Override
    public int removeBlogBusiJunkData() {
        junkResouceDao.removeBlogBusiJunkData();
        return 1;
    }
}
