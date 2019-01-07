package com.chenchuan.admin.resource.dao;

import com.chenchuan.admin.resource.vo.OssVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * oss dao
 */
@Mapper
@Repository
public interface OssDao {

    /**
     * 添加oss
     *
     * @param ossVo
     * @return 影响行数
     */
    int addOss(OssVo ossVo);

    /**
     * 添加模块和oss关联
     *
     * @param moduleOssAuths 添加的模块oss关联数据
     * @return 影响行数
     */
    int addModuleOssAuth(@Param("moduleOssAuths") List<Map<String, Object>> moduleOssAuths);

    /**
     * 根据模块删除模块对象存储关联（批量删除）
     *
     * @param moduleInfos 要删除的模块信息集合
     * @return 影响行数
     */
    int removeModuleOssAuthByModule(List<Map<String, Object>> moduleInfos);

    /**
     * 根据oss key删除oss
     *
     * @param ossKeys oss key集合
     * @return 影响行数
     */
    int removeModuleOssAuthByOssKeys(List<String> ossKeys);
}
