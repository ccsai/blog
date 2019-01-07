package com.chenchuan.admin;

import com.alibaba.fastjson.JSON;
import com.chenchuan.admin.blog.dao.LeaveMessageDao;
import com.chenchuan.admin.blog.service.ArticleCommentService;
import com.chenchuan.admin.blog.service.FriendlyLinkServie;
import com.chenchuan.admin.blog.service.LabelService;
import com.chenchuan.admin.blog.service.LeaveMessageService;
import com.chenchuan.admin.blog.vo.ArticleCommentVo;
import com.chenchuan.admin.blog.vo.FriendlyLinkVo;
import com.chenchuan.admin.blog.vo.LabelVo;
import com.chenchuan.admin.blog.vo.LeaveMessageVo;
import com.chenchuan.admin.index.service.IndexService;
import com.chenchuan.admin.sys.dao.MenuDao;
import com.chenchuan.admin.sys.dao.PermissionDao;
import com.chenchuan.admin.sys.dao.RoleDao;
import com.chenchuan.admin.sys.dao.UserDao;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.MenuService;
import com.chenchuan.admin.sys.service.PermissionService;
import com.chenchuan.admin.sys.service.RoleService;
import com.chenchuan.admin.sys.service.UserService;
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
    private UserService userService;

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

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private FriendlyLinkServie friendlyLinkServie;

    @Autowired
    private LabelService labelService;

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Test
    public void testPermissionDao() {
        Map<String,Object> map = new HashMap<>();
        map.put("targetUser","manager");
        map.put("sendUser","36dad18b-ded4-42aa-96bc-e45de5afbd18");
        map.put("isRead",0);
        LeaveMessageVo leaveMessageVo = new LeaveMessageVo();
        leaveMessageVo.setSendUser("manager");leaveMessageVo.setTargetUser("36dad18b-ded4-42aa-96bc-e45de5afbd18");
        leaveMessageVo.setMessage("安达市多");leaveMessageVo.setIsRead(0);leaveMessageVo.setCreateUser("admin");
        System.out.println(JSON.toJSONString(leaveMessageService.findLeaveMessageByUserId(map)));
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
