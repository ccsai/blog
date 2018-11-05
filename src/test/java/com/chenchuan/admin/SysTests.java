package com.chenchuan.admin;

import com.alibaba.fastjson.JSON;
import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.dao.RoleDao;
import com.chenchuan.admin.sys.dao.UserDao;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.RoleService;
import com.chenchuan.admin.sys.vo.PermissionVo;
import com.chenchuan.admin.sys.vo.RoleVo;
import com.chenchuan.admin.sys.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleService roleService;

    @Test
    public void testPermissionDao() {
        UserVo userVo = new UserVo();
        userVo.setLoginName("userTest");
        RoleVo roleVo = new RoleVo();
        roleVo.setRoleId("726d177a-9674-4d5f-af9b-083a6e399c3e");
        System.out.println(JSON.toJSONString(roleService.findRolesPermissionsByUser(userVo)));
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
