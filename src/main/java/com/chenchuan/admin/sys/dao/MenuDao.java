package com.chenchuan.admin.sys.dao;

import com.chenchuan.admin.sys.po.MenuPo;
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
     * 根据用户查询菜单树
     *
     * @param userVo
     * @return 菜单树
     */
    List<MenuVo> findMenuTreebyUser(UserVo userVo);

    /**
     * 查询所有菜单树
     *
     * @return 菜单树
     */
    List<MenuVo> findAllMenuTree();

    /**
     * 添加菜单
     *
     * @param menuVo
     * @return 影响行数
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
     * @return 影响行数
     */
    int editMenuByMenuId(MenuVo menuVo);

    /**
     * 查看当前菜单子菜单数量
     *
     * @param menuId 菜单编号
     * @return 子菜单个数
     */
    int findChildrenMenuByMenuId(String menuId);

    /**
     * 根据菜单编号删除菜单
     *
     * @param menuId 菜单编号
     * @return 影响行数
     */
    int removeMenuByMenuId(String menuId);
}
