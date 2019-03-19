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
import org.apache.shiro.UnavailableSecurityManagerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    public UserPo getCurrentLoginUserBaseInfo() {
        UserPo userPo;
        try {
            userPo = (UserPo) SecurityUtils.getSubject().getPrincipal();
        } catch (UnavailableSecurityManagerException e) {
            userPo = null;
        }
        return userPo;
    }

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
        //数据验证
        if (userVo.getLoginName() == null || userVo.getLoginName().length() < 1 || userVo.getLoginName().length() > 20) {
            throw new BaseException("用户名长度应在1到20位之间");
        } else if (userVo.getLoginName().indexOf("用户_") != -1) {
            throw new BaseException("该用户名不可用");
        }
        //加密前密码
        String password = userVo.getPassword();
        if (password == null || password.equals("")) {
            throw new BaseException("密码不能为空");
        } else if (!Pattern.matches(".{6,20}", password)) {
            throw new BaseException("密码长度应在6到20位之间");
        }
        if (userVo.getPhone() == null || userVo.getPhone().equals("")) {
            throw new BaseException("手机号码不能为空");
        } else if (!Pattern.matches("^1[0-9]\\d{9}$", userVo.getPhone())) {
            throw new BaseException("手机号码格式错误");
        }
        if (userVo.getEmail() == null || userVo.getEmail().equals("")) {
            throw new BaseException("邮箱不能为空");
        } else if (!Pattern.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", userVo.getEmail())) {
            throw new BaseException("邮箱格式错误");
        }

        //用于验证用户名重名
        userVo.setUserId(null);
        //判断输入的用户明是否重复
        int loginNameIsUsed = userDao.findLoginNameNumberByLoginName(userVo);
        if (loginNameIsUsed > 0) {
            throw new BaseException("该用户名已经被占用，请重新输入！");
        }
        //主键
        String primaryKey = UuidUtil.getUuid();
        userVo.setUserId(primaryKey);
        //当前登录用户编号
        UserPo curUser = getCurrentLoginUserBaseInfo();
        if (curUser == null || curUser.equals("")) {
            userVo.setCreateUser(primaryKey);
            userVo.setModifyUser(primaryKey);
        } else if (curUser != null || !curUser.equals("")) {
            userVo.setCreateUser(curUser.getUserId());
            userVo.setModifyUser(curUser.getUserId());
        }
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
        //数据验证
        if (userVo.getLoginName() == null || userVo.getLoginName().length() < 1 || userVo.getLoginName().length() > 20) {
            throw new BaseException("用户名长度应在1到20位之间");
        } else if (userVo.getLoginName().indexOf("用户_") != -1) {
            throw new BaseException("该用户名不可用");
        }
        if (userVo.getPhone() == null || userVo.getPhone().equals("")) {
            throw new BaseException("手机号码不能为空");
        } else if (!Pattern.matches("^1[0-9]\\d{9}$", userVo.getPhone())) {
            throw new BaseException("手机号码格式错误");
        }
        if (userVo.getEmail() == null || userVo.getEmail().equals("")) {
            throw new BaseException("邮箱不能为空");
        } else if (!Pattern.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", userVo.getEmail())) {
            throw new BaseException("邮箱格式错误");
        }

        //判断输入的用户明是否重复
        int loginNameIsUsed = userDao.findLoginNameNumberByLoginName(userVo);
        if (loginNameIsUsed > 0) {
            throw new BaseException("该用户名已经被占用，请重新输入！");
        }
        //当前用户
        userVo.setModifyUser(getCurrentLoginUserBaseInfo().getUserId());
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

    @Override
    public List<UserVo> findIsHaveNewLeaveMessageByManager(UserVo userVo) {
        return userDao.findIsHaveNewLeaveMessageByManager(userVo);
    }

    @Override
    @Transactional
    public int register(UserVo userVo) {
        userVo.setLoginType(0);
        userVo.setStatus(1);
        //数据验证
        if (userVo.getPassword() != null && !userVo.getPassword().equals("") && !userVo.getPassword().equals(userVo.getTwicePassword())) {
            throw new BaseException("两次输入的密码不一致");
        }
        //添加用户
        return addUser(userVo);
    }

    @Override
    @Transactional
    public int modifyUserInfo(UserVo userVo) {
        //数据验证
        if (userVo.getPassword() != null && !userVo.getPassword().equals("")) {
            if (!Pattern.matches(".{6,20}", userVo.getPassword()))
                throw new BaseException("密码长度应在6到20位之间");
            if (!userVo.getPassword().equals(userVo.getTwicePassword()))
                throw new BaseException("两次输入的密码不一致");
        }
        return editUserByUserId(userVo);
    }

    @Override
    public UserVo findUserByPhoneAndEmail(UserVo userVo) {
        //找到用户
        UserVo user = userDao.findUserByPhoneAndEmail(userVo);
        if (user == null) {
            throw new BaseException("不存在匹配的用户");
        }
        user.setPassword(null);
        return user;
    }

    @Override
    @Transactional
    public int modifyUserInfoByforgetPwd(UserVo userVo) {
        //数据验证
        if (userVo.getUserId() == null || userVo.getUserId().equals("")) {
            throw new BaseException("请求参数出错");
        }
        if (userVo.getLoginName() == null || userVo.getLoginName().equals("")) {
            throw new BaseException("用户名不能为空");
        }
        if (userVo.getPassword() == null || userVo.getPassword().equals("")) {
            throw new BaseException("密码不能为空");
        } else if (!Pattern.matches(".{6,20}", userVo.getPassword())) {
            throw new BaseException("密码长度在6到20位之间");
        } else if (!userVo.getPassword().equals(userVo.getTwicePassword())) {
            throw new BaseException("两次输入的密码不相等");
        }
        //密码加密
        userVo.setPassword(HashedUtil.getSimpleHash(userVo.getLoginName(), userVo.getPassword(), securityConfig.getHashAlgorithmName(), securityConfig.getHashIterations()).toString());
        //修改
        int result = userDao.editUserByUserId(userVo);
        if (result <= 0) {
            throw new BaseException("修改用户名密码失败");
        }
        return 1;
    }
}