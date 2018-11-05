package com.chenchuan.admin.sys.dao;

import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 系统用户dao
 */
@Mapper
@Repository
public interface UserDao {

    /**
     * 根据条件查询用户
     *
     * @param userVo
     * @return 用户
     */
    UserPo findUserByLoginNameAndPassword(UserVo userVo);

}
