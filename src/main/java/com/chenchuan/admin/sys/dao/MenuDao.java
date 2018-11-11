package com.chenchuan.admin.sys.dao;

import com.chenchuan.admin.sys.vo.MenuVo;
import com.chenchuan.admin.sys.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单dao
 */
@Mapper
@Repository
public interface MenuDao {

    /**
     * 查询菜单树
     *
     * @param userVo
     * @return 菜单树
     */
    List<MenuVo> findMenuTreebyUser(UserVo userVo);
}
