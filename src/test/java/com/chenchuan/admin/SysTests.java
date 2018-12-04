package com.chenchuan.admin;

import com.alibaba.fastjson.JSON;
import com.chenchuan.admin.index.service.IndexService;
import com.chenchuan.admin.sys.dao.MenuDao;
import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.dao.RoleDao;
import com.chenchuan.admin.sys.dao.UserDao;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.MenuService;
import com.chenchuan.admin.sys.service.PermissionService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private PermissionService permissionService;

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
        Map<String,Object> map = new HashMap<>();
        map.put("roleId","aa");
        List<String> list = new ArrayList<>();
        list.add("bb");list.add("cc");
        map.put("menuIds",list);
        roleDao.addRoleMenuAuth(map);
        //System.out.println(JSON.toJSONString(menuService.findRoleMenuPermissionAuthTree("c57c2eca-bc2a-4e16-893a-7698a427195f")));
//        PermissionVo permissionVo = new PermissionVo();
//        permissionVo.setPermissionName("1");permissionVo.setPermissionDes("1");
//        permissionVo.setUrl("/1/1");permissionVo.setUrlType("/1");
//        permissionVo.setMenuId("s1111-11111");permissionVo.setPermissionId("a5d0b2fe-7291-4a09-b39d-b0d2d8db5582");
//        System.out.println(permissionService.removePermissionByPermissionId("a5d0b2fe-7291-4a09-b39d-b0d2d8db5582"));
        //System.out.println(JSON.toJSONString(permissionService.findPermissionByPermissionId("7f9a3463-7ec2-4439-9770-0899c4795683")));
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
