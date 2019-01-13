package com.chenchuan.admin.resource.service.impl;

import com.chenchuan.admin.resource.dao.JunkResouceDao;
import com.chenchuan.admin.resource.service.JunkResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 垃圾数据清理service实现
 */
@Service
public class JunkResouceServiceImpl implements JunkResouceService {

    @Autowired
    private JunkResouceDao junkResouceDao;


    @Override
    @Transactional
    public int removeSecurityJunkData() {
        junkResouceDao.removeSecurityJunkData();
        return 1;
    }

    @Override
    @Transactional
    public int removeBlogBusiJunkData() {
        junkResouceDao.removeBlogBusiJunkData();
        return 1;
    }
}
