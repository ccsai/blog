package com.chenchuan.admin.blog.dao;

import com.chenchuan.admin.blog.vo.LeaveMessageVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 留言dao
 */
@Mapper
@Repository
public interface LeaveMessageDao {

    /**
     * 根据用户编号查询自己创建和收到的留言
     *
     * @param targetSendUserAndLeaveMessageInfo 用户编号与留言信息targetUser、sendUser
     * @return 用户相关留言集合
     */
    List<LeaveMessageVo> findLeaveMessageByUserId(Map<String, Object> targetSendUserAndLeaveMessageInfo);

    /**
     * 管理员发送留言
     *
     * @param leaveMessageVo
     * @return 影响行数
     */
    int addLeaveMessage(LeaveMessageVo leaveMessageVo);

    /**
     * 根据编号批量删除留言
     *
     * @param leaveMessageIds 留言编号字符串
     * @return 影响行数
     */
    int removeLeaveMessagesByLeaveMessageIds(String leaveMessageIds);

    /**
     * 根据留言接受人编号修改留言状态（未读->已读）
     *
     * @param targetAndSendUser 目标用户和发送用户编号
     * @return 影响行数
     */
    int editLeaveMessageIsReadByUserId(Map<String, Object> targetAndSendUser);
}
