package com.chenchuan.config.quartz;

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
                .withIdentity("removeSupportRecod")
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
                .withIdentity("removeSupportRecod")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 1 * ? *"))
                .build();
    }
}
