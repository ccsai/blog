package com.chenchuan.admin.sys.service.impl;

import com.chenchuan.admin.sys.dao.MenuDao;
import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.po.MenuPo;
import com.chenchuan.admin.sys.service.MenuService;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.MenuVo;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单service实现类
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserService userService;


    @Override
    public List<MenuVo> findMenuTreebyUser(UserVo userVo) {
        //用户可查看菜单与子菜单数量
        List<MenuVo> menuList = menuDao.findMenuTreebyUser(userVo);
        //根据菜单列表生成树
        return createMenuTree(menuList);
    }

    @Override
    public List<MenuVo> findAllMenuTree() {
        //所有菜单级菜单数量
        List<MenuVo> menuList = menuDao.findAllMenuTree();
        //根据菜单列表生成树
        return createMenuTree(menuList);
    }

    @Override
    @Transactional
    public int addMenu(MenuVo menuVo) {
        menuVo.setMenuId(UuidUtil.getUuid());//主键
        //当前登录用户名
        String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
        menuVo.setCreateUser(userId);
        menuVo.setModifyUser(userId);
        //添加菜单
        int result = menuDao.addMenu(menuVo);
        if (result == 0) {
            throw new BaseException("未添加菜单，请稍后重试或联系管理员！");
        }
        return result;
    }

    @Override
    public MenuPo findMenuByMenuId(String menuId) {
        return menuDao.findMenuByMenuId(menuId);
    }

    @Override
    @Transactional
    public int editMenuByMenuId(MenuVo menuVo) {
        menuVo.setModifyUser(userService.getCurrentLoginUserBaseInfo().getUserId());
        //修改菜单
        int result = menuDao.editMenuByMenuId(menuVo);
        if (result == 0) {
            throw new BaseException("未修改菜单，请稍后重试或联系管理员！");
        }
        return result;
    }

    @Override
    @Transactional
    public int removeMenuByMenuId(String menuId) {
        if (menuDao.findChildrenMenuByMenuId(menuId) > 0) {
            throw new BaseException("有子菜单不可删除！");
        }
        if (permissionDao.findPermissionNumberByMemu(menuId) > 0) {
            throw new BaseException("该菜单和菜单下的功能已授权，不可删除！");
        }
        //删除菜单
        int result = menuDao.removeMenuByMenuId(menuId);
        if (result == 0) {
            throw new BaseException("未删除菜单！");
        }
        //删除菜单角色关联
        menuDao.removeRoleMenuAuthByMenuId(menuId);
        return result;
    }

    @Override
    public List<MenuVo> findRoleMenuPermissionAuthTree(String roleId) {
        //角色与菜单权限关系树的的菜单树
        List<MenuVo> menuList = menuDao.findRoleMenuPermissionAuthTree(roleId);
        //生成菜单树
        return createMenuTree(menuList);
    }

    /**
     * 根据菜单列表生成树
     *
     * @param menuList 菜单列表
     * @return 菜单树
     */
    private List<MenuVo> createMenuTree(List<MenuVo> menuList) {
        //所有一级菜单
        List<MenuVo> rootMenuList = new ArrayList<>();
        if (menuList != null && menuList.size() != 0) {
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
        if (curMenu.getChildrenNumber() != null && curMenu.getChildrenNumber() > 0) {
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