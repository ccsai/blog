package com.chenchuan.admin;

import com.alibaba.fastjson.JSON;
import com.chenchuan.admin.index.service.IndexService;
import com.chenchuan.admin.sys.dao.MenuDao;
import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.dao.RoleDao;
import com.chenchuan.admin.sys.dao.UserDao;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.MenuService;
import com.chenchuan.admin.sys.service.RoleService;
import com.chenchuan.admin.sys.vo.MenuVo;
import com.chenchuan.admin.sys.vo.PermissionVo;
import com.chenchuan.admin.sys.vo.RoleVo;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysTests {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private MenuService menuService;

    @Autowired
    private IndexService indexService;

    @Test
    public void testPermissionDao() {
        UserVo userVo = new UserVo();
        userVo.setLoginName("admin");
        userVo.setPassword("333333");
        MenuVo menuVo = new MenuVo();
        menuVo.setMenuId("aaaaa");menuVo.setStatus(0);menuVo.setpMenuId("cc");menuVo.setMenuDes("descc");
        menuVo.setSortNo(121);menuVo.setCreateUser("cccc");menuVo.setModifyUser("ccc");menuVo.setMenuName("ccc");
        System.out.println(menuService.removeMenuByMenuId("aaaaa"));
//        System.out.println(JSON.toJSONString(menuService.findMenuByMenuId("aaaaa")));
//        RoleVo roleVo = new RoleVo();
//        roleVo.setRoleId("726d177a-9674-4d5f-af9b-083a6e399c3e");
//        System.out.println(JSON.toJSONString(roleService.findRolesPermissionsByUser(userVo)));
        //System.out.println(permissionDao.findPermissionByUser(userVo));
        //System.out.println(JSON.toJSONString(permissionDao.findPermissionList(new PermissionVo())));
    }

    //@Test
    public void testUserDao() {
        UserVo userVo = new UserVo();
        userVo.setLoginName("admin");
        userVo.setPassword("111111");
        UserPo userPo = userDao.findUserByLoginNameAndPassword(userVo);
        System.out.println(JSON.toJSONString(userPo));
    }

}
