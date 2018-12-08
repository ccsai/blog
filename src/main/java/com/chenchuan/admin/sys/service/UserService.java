package com.chenchuan.admin.sys.service;

import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 用户servica
 */
public interface UserService {

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
}
