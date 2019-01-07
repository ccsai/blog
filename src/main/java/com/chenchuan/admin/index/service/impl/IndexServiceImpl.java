package com.chenchuan.admin.index.service.impl;

import com.chenchuan.admin.index.service.IndexService;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.MenuService;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台首页service实现
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;


    @Override
    public Map<String, Object> loadIndexPageData() {
        //首页所需数据
        Map<String, Object> map = new HashMap<>();
        //获取当前登录用户
        UserPo userPo = userService.getCurrentLoginUserBaseInfo();
        UserVo userVo = new UserVo();
        userVo.setLoginName(userPo.getLoginName());
        //用户能看到的菜单
        map.put("menuTree", menuService.findMenuTreebyUser(userVo));
        return map;
    }
}