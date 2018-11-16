package com.chenchuan.admin.sys.service;

import com.chenchuan.admin.sys.po.MenuPo;
import com.chenchuan.admin.sys.vo.MenuVo;
import com.chenchuan.admin.sys.vo.UserVo;

import java.util.List;

/**
 * 菜单service接口
 */
public interface MenuService {

    /**
     * 根据用户生成菜单树
     *
     * @param userVo
     * @return 菜单树结构
     */
    List<MenuVo> findMenuTreebyUser(UserVo userVo);

    /**
     * 生成所有菜单树
     *
     * @return 菜单树
     */
    List<MenuVo> findAllMenuTree();

    /**
     * 添加菜单
     *
     * @param menuVo
     * @return 操作状态
     */
    int addMenu(MenuVo menuVo);

    /**
     * 根据菜单编号查询菜单详情
     *
     * @param menuId 菜单编号
     * @return 菜单详情
     */
    MenuPo findMenuByMenuId(String menuId);

    /**
     * 根据菜单编号修改菜单
     *
     * @param menuVo
     * @return 修改成功状态
     */
    int editMenuByMenuId(MenuVo menuVo);

    /**
     * 根据菜单编号删除菜单
     *
     * @param menuId 菜单编号
     * @return 影响行数
     */
    int removeMenuByMenuId(String menuId);
}
