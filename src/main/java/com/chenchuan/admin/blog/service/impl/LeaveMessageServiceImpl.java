package com.chenchuan.admin.blog.service.impl;

import com.chenchuan.admin.blog.dao.LeaveMessageDao;
import com.chenchuan.admin.blog.service.LeaveMessageService;
import com.chenchuan.admin.blog.vo.LeaveMessageVo;
import com.chenchuan.admin.resource.dao.OssDao;
import com.chenchuan.admin.sys.service.UserService;
import com.chenchuan.common.exception.BaseException;
import com.chenchuan.common.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 留言service实现
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Autowired
    private UserService userService;

    @Autowired
    private OssDao ossDao;


    @Override
    @Transactional
    public List<LeaveMessageVo> findLeaveMessageByUserId(Map<String, Object> targetSendUserAndIsReadInfo) {
        //用户编号与留言已读信息
        Map<String, Object> targetSendUserAndLeaveMessageInfo = new HashMap<>();
        //留言查看者（接收者）编号
        String targetUser = (String) targetSendUserAndIsReadInfo.get("targetUser");
        if (targetUser == null || targetUser.equals("")) {
            throw new BaseException("没有当前用户信息！");
        }
        //留言者
        String sendUser = (String) targetSendUserAndIsReadInfo.get("sendUser");
        if (sendUser == null || sendUser.equals("")) {
            throw new BaseException("没有留言用户信息！");
        }
        //根据接收和发送用户查询自己发送和收到的留言
        targetSendUserAndLeaveMessageInfo.put("targetUser", targetUser);
        targetSendUserAndLeaveMessageInfo.put("sendUser", sendUser);
        List<LeaveMessageVo> leaveMessageList = leaveMessageDao.findLeaveMessageByUserId(targetSendUserAndLeaveMessageInfo);
        //未读留言变为已读留言
        if (leaveMessageList != null && leaveMessageList.size() > 0) {
            leaveMessageDao.editLeaveMessageIsReadByUserId(targetSendUserAndIsReadInfo);
        }
        return leaveMessageList;
    }

    @Override
    @Transactional
    public int addLeaveMessage(LeaveMessageVo leaveMessageVo, String[] ossKeys) {
        //留言编号
        String leaveMessageId = UuidUtil.getUuid();
        leaveMessageVo.setLeaveMessageId(leaveMessageId);
        //当前登录用户
        String userId = userService.getCurrentLoginUserBaseInfo().getUserId();
        leaveMessageVo.setCreateUser(userId);
        //添加留言
        int affectRows = leaveMessageDao.addLeaveMessage(leaveMessageVo);
        if (affectRows <= 0) {
            throw new BaseException("发送留言失败！<br>请稍后重试或联系管理员！");
        }
        //添加留言与oss的关联
        if (ossKeys != null && ossKeys.length > 0) {
            List<Map<String, Object>> moduleOssAuths = new ArrayList<>();
            for (String key : ossKeys) {
                Map<String, Object> moduleOssAuth = new HashMap<>();
                moduleOssAuth.put("moduleId", leaveMessageId);
                moduleOssAuth.put("ossKey", key);
                moduleOssAuth.put("moduleType", 4);
                moduleOssAuths.add(moduleOssAuth);
            }
            //关联
            ossDao.addModuleOssAuth(moduleOssAuths);
        }
        return 1;
    }

    @Override
    @Transactional
    public int removeLeaveMessagesByLeaveMessageIds(String leaveMessageIds) {
        //删除留言
        int affectRows = leaveMessageDao.removeLeaveMessagesByLeaveMessageIds(leaveMessageIds);
        if (affectRows <= 0) {
            throw new BaseException("没有留言被删除！<br>请稍后重试或联系管理员！");
        }
        //留言编号数组（'abc'）格式
        String[] leaveMessageIdArr = leaveMessageIds.split(",");
        if (leaveMessageIdArr != null && leaveMessageIdArr.length > 0) {
            //删除的留言信息集合
            List<Map<String, Object>> leaveMessageInfos = new ArrayList<>();
            for (String leaveMessageId : leaveMessageIdArr) {
                //验证编号格式
                if (!Pattern.matches("^'.+'$", leaveMessageId)) {
                    throw new BaseException("留言删除失败！<br>请稍后重试或联系管理员！");
                }
                //获取'abc'格式的编号长度
                int leaveMessageLength = leaveMessageId.length();
                //当前留言信息
                Map<String, Object> leaveMessageInfo = new HashMap<>();
                leaveMessageInfo.put("moduleId", leaveMessageId.substring(1, leaveMessageLength - 1));
                leaveMessageInfo.put("moduleType", 4);
                leaveMessageInfos.add(leaveMessageInfo);
            }
            //根据模块信息删除留言oss关联
            ossDao.removeModuleOssAuthByModule(leaveMessageInfos);
        }
        return 1;
    }
}
