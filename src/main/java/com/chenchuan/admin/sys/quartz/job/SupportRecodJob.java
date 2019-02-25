package com.chenchuan.admin.sys.quartz.job;

import com.chenchuan.admin.blog.dao.SupportDao;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 踩、赞定时任务
 */
public class SupportRecodJob extends QuartzJobBean {

    @Autowired
    private SupportDao supportDao;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //删除踩、赞的记录
        supportDao.removeBeforeLastMonthSupportRecord();
    }
}
