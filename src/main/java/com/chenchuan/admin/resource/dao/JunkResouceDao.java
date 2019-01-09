package com.chenchuan.admin.resource.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 垃圾文件、数据清理Dao
 */
@Mapper
@Repository
public interface JunkResouceDao {

    /**
     * 安全垃圾数据清理
     */
    void removeSecurityJunkData();

    /**
     * 博客业务垃圾数据清理
     */
    void removeBlogBusiJunkData();
}
