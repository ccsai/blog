package com.chenchuan.admin.sys.controller;

import com.chenchuan.admin.sys.service.MenuService;
import com.chenchuan.admin.sys.vo.MenuVo;
import com.chenchuan.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/index")
    public String index() {
        return "/admin/sys/menu/index";
    }

    /**
     * 获取所有菜单树
     *
     * @return 菜单树
     */
    @GetMapping("/findAllMenuTree")
    @ResponseBody
    public Map<String, Object> findAllMenuTree() {
        Map<String, Object> map = new HashMap<>();
        //加载所有菜单树
        map.put("menuTree", menuService.findAllMenuTree());
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 添加菜单
     *
     * @param menuVo
     * @return 操作结果
     */
    @PostMapping("/addMenu")
    @ResponseBody
    public Map<String, Object> addMenu(MenuVo menuVo) {
        Map<String, Object> map = new HashMap<>();
        //添加菜单
        map.put("resultCode", menuService.addMenu(menuVo));
        return map;
    }

    /**
     * 根据菜单编号查询菜单详情
     *
     * @param menuId 菜单编号
     * @return 菜单详情
     */
    @GetMapping("/findMenuByMenuId")
    @ResponseBody
    public Map<String, Object> findMenuByMenuId(String menuId) {
        Map<String, Object> map = new HashMap<>();
        //菜单详情
        map.put("menuDetail", menuService.findMenuByMenuId(menuId));
        map.put("resultCode", 1);
        return map;
    }

    /**
     * 根据菜单编号修改菜单
     *
     * @param menuVo
     * @return 操作结果
     */
    @PostMapping("/editMenuByMenuId")
    @ResponseBody
    public Map<String, Object> editMenuByMenuId(MenuVo menuVo) {
        Map<String, Object> map = new HashMap<>();
        //修改菜单
        map.put("resultCode", menuService.editMenuByMenuId(menuVo));
        return map;
    }

    /**
     * 根据菜单编号删除菜单
     *
     * @param menuId 菜单编号
     * @return 删除结果
     */
    @PostMapping("/removeMenuByMenuId")
    @ResponseBody
    public Map<String, Object> removeMenuByMenuId(String menuId) {
        Map<String, Object> map = new HashMap<>();
        //删除菜单
        map.put("resultCode", menuService.removeMenuByMenuId(menuId));
        return map;
    }
}
