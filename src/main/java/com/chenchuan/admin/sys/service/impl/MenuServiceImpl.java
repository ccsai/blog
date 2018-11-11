package com.chenchuan.admin.sys.service.impl;

import com.chenchuan.admin.sys.dao.MenuDao;
import com.chenchuan.admin.sys.service.MenuService;
import com.chenchuan.admin.sys.vo.MenuVo;
import com.chenchuan.admin.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单service实现类
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Override
    public List<MenuVo> findMenuTreebyUser(UserVo userVo) {
        //所有菜单与子菜单数量
        List<MenuVo> menuList = menuDao.findMenuTreebyUser(userVo);
        //所有一级菜单
        List<MenuVo> rootMenuList = new ArrayList<>();
        if (!(rootMenuList == null && rootMenuList.size() == 0)) {
            for (MenuVo m : menuList) {
                if (m.getpMenuId().equals("root")) {
                    //加载一级目录所有菜单
                    m.setChildren(findMenuChildren(m, menuList));
                    rootMenuList.add(m);
                }
            }
        }
        return rootMenuList;
    }

    /**
     * 组装菜单树
     *
     * @param curMenu  当前菜单
     * @param menuList 所有菜单
     * @return 所有子菜单集合
     */
    private List<MenuVo> findMenuChildren(MenuVo curMenu, List<MenuVo> menuList) {
        //子菜单集合
        List<MenuVo> childrenMenuList = new ArrayList<>();
        if (curMenu.getChildrenNumber() > 0) {
            for (MenuVo m : menuList) {
                if (curMenu.getMenuId().equals(m.getpMenuId())) {
                    //设置子菜单
                    m.setChildren(findMenuChildren(m, menuList));
                    //添加子菜单入子菜单集合
                    childrenMenuList.add(m);
                }
            }
        }
        return childrenMenuList;
    }
}
