package com.chenchuan.admin.sys.service;

import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户servica
 */
public interface UserService {

    /**
     * 得到当前登录用户的基本信息
     *
     * @return 用户po
     */
    UserPo getCurrentLoginUserBaseInfo();

    /**
     * 分有查询所有用户
     *
     * @param userVo
     * @return 用户列表分页信息
     */
    PageInfo<UserPo> findUserListByPage(UserVo userVo);

    /**
     * 添加用户
     *
     * @param userVo
     * @return 添加结果状态
     */
    int addUser(UserVo userVo);

    /**
     * 根据用户编号查询用户信息
     *
     * @param userId 用户编号
     * @return 用户信息
     */
    UserVo findUserByUserId(String userId);

    /**
     * 根据用户编号修改用户
     *
     * @param userVo
     * @return 影响行数
     */
    int editUserByUserId(UserVo userVo);

    /**
     * 根据用户编号删除用户
     *
     * @param userId 用户编号
     * @return 删除结果状态
     */
    int removeUserByUserId(String userId);

    /**
     * 添加用户角色关联
     *
     * @param userIdRoleIdsAuthObj 具有用户角色编号关系的对象
     * @return 添加结果状态
     */
    int addUserRoleAuth(Map<String, Object> userIdRoleIdsAuthObj);

    /**
     * 管理员查询所有用户以及是否有新留言
     *
     * @param userVo
     * @return 带未读留言的用户集合
     */
    List<UserVo> findIsHaveNewLeaveMessageByManager(UserVo userVo);

    /**
     * 前台注册用户
     *
     * @param userVo
     * @return 注册状态
     */
    int register(UserVo userVo);

    /**
     * 前台用户修改信息
     *
     * @param userVo
     * @return 修改状态
     */
    int modifyUserInfo(UserVo userVo);

    /**
     * 找回密码验证（通过手机邮箱查询用户）
     *
     * @param userVo
     * @return 对应用户
     */
    UserVo findUserByPhoneAndEmail(UserVo userVo);

    /**
     * 忘记密码修改信息
     * @param userVo
     * @return
     */
    int modifyUserInfoByforgetPwd(UserVo userVo);
}
