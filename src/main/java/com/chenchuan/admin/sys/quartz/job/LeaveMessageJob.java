package com.chenchuan.admin.sys.quartz.job;

import com.chenchuan.admin.blog.dao.LeaveMessageDao;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 留言定时任务
 */
public class LeaveMessageJob extends QuartzJobBean {

    @Autowired
    private LeaveMessageDao leaveMessageDao;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //删除留言记录
        leaveMessageDao.removeLeaveMessageBeforeSomeTime();
    }
}
