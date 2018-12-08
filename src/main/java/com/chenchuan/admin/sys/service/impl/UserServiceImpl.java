package com.chenchuan.admin.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chenchuan.admin.sys.dao.UserDao;
import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.admin.sys.vo.UserVo;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.util.HashedUtil;
import com.chenchuan.common.util.UuidUtil;
import com.chenchuan.config.shiro.SecurityConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户service实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityConfig securityConfig;


    @Override
    public PageInfo<UserPo> findUserListByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getPage(), userVo.getRows());
        //用户列表
        List<UserPo> userList = userDao.findUserList(userVo);
        return new PageInfo<>(userList);
    }

    @Override
    @Transactional
    public int addUser(UserVo userVo) {
        //判断输入的用户明是否重复
        int loginNameIsUsed = userDao.findLoginNameNumberByLoginName(userVo);
        if (loginNameIsUsed > 0) {
            throw new BaseException("该用户名已经被占用，请重新输入！");
        }
        //主键
        userVo.setUserId(UuidUtil.getUuid());
        //当前登录用户编号
        String userId = ((UserPo) SecurityUtils.getSubject().getPrincipal()).getUserId();
        userVo.setCreateUser(userId);
        userVo.setModifyUser(userId);
        //加密前密码
        String password = userVo.getPassword();
        //密码加密
        if (password != null && password != "") {
            userVo.setPassword(HashedUtil.getSimpleHash(userVo.getLoginName(), password, securityConfig.getHashAlgorithmName(), securityConfig.getHashIterations()).toString());
        }
        //添加用户
        int result = userDao.addUser(userVo);
        if (result == 0) {
            throw new BaseException("未添加用户，请稍后重试或联系管理员！");
        }
        return result;
    }

    @Override
    public UserVo findUserByUserId(String userId) {
        //用户信息
        UserVo userVo = userDao.findUserByUserId(userId);
        if (userVo == null) {
            throw new BaseException("用户可能被删除，请刷新表格查看。如确认存在，请联系管理员！");
        }
        //不返回密码
        userVo.setPassword(null);
        return userVo;
    }

    @Override
    @Transactional
    public int editUserByUserId(UserVo userVo) {
        //判断输入的用户明是否重复
        int loginNameIsUsed = userDao.findLoginNameNumberByLoginName(userVo);
        if (loginNameIsUsed > 0) {
            throw new BaseException("该用户名已经被占用，请重新输入！");
        }
        //当前用户
        userVo.setModifyUser(((UserPo) SecurityUtils.getSubject().getPrincipal()).getUserId());
        //加密前密码
        String password = userVo.getPassword();
        //密码加密
        if (password != null && password != "") {
            userVo.setPassword(HashedUtil.getSimpleHash(userVo.getLoginName(), password, securityConfig.getHashAlgorithmName(), securityConfig.getHashIterations()).toString());
        }
        //修改用户
        int result = userDao.editUserByUserId(userVo);
        if (result == 0) {
            throw new BaseException("未修改用户，请稍后重试或联系管理员！");
        }
        return result;
    }

    @Override
    @Transactional
    public int removeUserByUserId(String userId) {
        //删除用户
        int result = userDao.removeUserByUserId(userId);
        if (result == 0) {
            throw new BaseException("未删除用户，请稍后重试或联系管理员！");
        }
        //删除用户角色关联关系
        userDao.removeUserRoleAuthByUserId(userId);
        return result;
    }

    @Override
    @Transactional
    public int addUserRoleAuth(Map<String, Object> userIdRoleIdsAuthObj) {
        //用户编号
        String userId = (String) userIdRoleIdsAuthObj.get("userId");
        //删除历史用户角色关联
        userDao.removeUserRoleAuthByUserId(userId);
        //用户编号和角色编号关联集合
        Map<String, Object> userIdRoleIdsAuth = new HashMap<>();
        userIdRoleIdsAuth.put("userId", userId);
        //角色编号集合
        JSONArray roleIds = JSON.parseArray((String) userIdRoleIdsAuthObj.get("roleIds"));
        if (roleIds != null && roleIds.size() != 0) {
            userIdRoleIdsAuth.put("roleIds", roleIds);
            userDao.addUserRoleAuth(userIdRoleIdsAuth);
        }
        return 1;
    }
}