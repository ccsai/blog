package com.chenchuan.config.quartz;

import com.chenchuan.admin.sys.quartz.job.LeaveMessageJob;
import com.chenchuan.admin.sys.quartz.job.SupportRecodJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quartz配置类
 */
@Configuration
public class QuartzConfiguration {

    /**
     * 删除赞、踩记录的定时任务配置
     *
     * @return JobDetail任务详情实例
     */
    @Bean
    public JobDetail removeSupportRecordJob() {
        return JobBuilder.newJob(SupportRecodJob.class)
                .withIdentity("removeSupportRecordJob")
                .storeDurably()
                .build();
    }

    /**
     * 删除赞、踩记录的定时任务的触发器设置
     *
     * @return 触发器实例
     */
    @Bean
    public Trigger removeSupportRecordTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(removeSupportRecordJob())
                .withIdentity("removeSupportRecordTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?"))
                .build();
    }

    /**
     * 删除留言记录任务详情
     *
     * @return JobDetail详情
     */
    @Bean
    public JobDetail removeLeaveMessageJob() {
        return JobBuilder.newJob(LeaveMessageJob.class)
                .withIdentity("removeLeaveMessageJob")
                .storeDurably()
                .build();
    }

    /**
     * 删除留言记录任务触发器配置
     *
     * @return 触发器实例
     */
    @Bean
    public Trigger removeLeaveMessageTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(removeLeaveMessageJob())
                .withIdentity("removeLeaveMessageTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?"))
                .build();
    }
}
