package com.chenchuan.admin.sys.service;

import com.chenchuan.admin.sys.vo.MenuVo;
import com.chenchuan.admin.sys.vo.UserVo;

import java.util.List;

/**
 * 菜单service接口
 */
public interface MenuService {

    /**
     * 生成菜单树
     *
     * @param userVo
     * @return 菜单树结构
     */
    List<MenuVo> findMenuTreebyUser(UserVo userVo);
}
