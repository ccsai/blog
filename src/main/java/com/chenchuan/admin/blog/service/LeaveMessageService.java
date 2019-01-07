package com.chenchuan.admin.blog.service;

import com.chenchuan.admin.blog.vo.LeaveMessageVo;

import java.util.List;
import java.util.Map;

/**
 * 留言service
 */
public interface LeaveMessageService {

    /**
     * 根据接收留言的用户编号查询自己发送和收到的留言
     *
     * @param targetSendUserAndIsReadInfo 用户编号与留言已读信息 targetUser、sendUser
     * @return 用户相关留言集合
     */
    List<LeaveMessageVo> findLeaveMessageByUserId(Map<String, Object> targetSendUserAndIsReadInfo);

    /**
     * 添加留言
     *
     * @param leaveMessageVo
     * @param ossKeys        oss key
     * @return 添加状态
     */
    int addLeaveMessage(LeaveMessageVo leaveMessageVo, String[] ossKeys);

    /**
     * 根据编号批量删除留言
     *
     * @param leaveMessageIds 留言编号字符串
     * @return 删除状态
     */
    int removeLeaveMessagesByLeaveMessageIds(String leaveMessageIds);
}
