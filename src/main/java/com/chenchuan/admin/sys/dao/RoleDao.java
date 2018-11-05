package com.chenchuan.admin.sys.dao;

import com.chenchuan.admin.sys.po.RolePo;
import com.chenchuan.admin.sys.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色dao
 */
@Mapper
@Repository
public interface RoleDao {


    /**
     * 根据用户查询角色
     *
     * @param userVo
     * @return 当前用户角色集合
     */
    List<RolePo> findRolesByUser(UserVo userVo);

}
