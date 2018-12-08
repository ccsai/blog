package com.chenchuan.admin.sys.dao;

import com.chenchuan.admin.sys.po.UserPo;
import com.chenchuan.admin.sys.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 系统用户dao
 */
@Mapper
@Repository
public interface UserDao {

    /**
     * 根据条件查询用户
     *
     * @param userVo
     * @return 用户
     */
    UserPo findUserByLoginNameAndPassword(UserVo userVo);

    /**
     * 查询所有用户列表
     *
     * @param userVo
     * @return 用户列表
     */
    List<UserPo> findUserList(UserVo userVo);

    /**
     * 得到对应用户名是否存在
     *
     * @param userVo
     * @return 重复行数
     */
    int findLoginNameNumberByLoginName(UserVo userVo);

    /**
     * 添加用户
     *
     * @param userVo
     * @return 影响行数
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
     * @return 影响行数
     */
    int removeUserByUserId(String userId);

    /**
     * 根据用户编号删除用户角色关联
     *
     * @param userId 用户编号
     * @return 影响行数
     */
    int removeUserRoleAuthByUserId(String userId);

    /**
     * 添加用户角色关联
     *
     * @param userIdRoleIdsAuth 具有用户角色编号关系的对象
     * @return 影响行数
     */
    int addUserRoleAuth(Map<String, Object> userIdRoleIdsAuth);
}
