package com.datapot.indicator.etl.config;


import com.datapot.indicator.etl.timer.HelloWordJob;
import com.datapot.indicator.etl.utils.QuartzManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    /**
     * @函数名称：scheduleJobs
     * @创建日期：2017年9月11日
     * @功能说明：定义quartz调度工厂
     * @参数说明：
     * @返回说明：void
     */
    public void scheduleJobs() throws SchedulerException {
        startJobs();
    }

    private void startJobs() throws SchedulerException {
        try {
            Object[] params = new Object[]{};
            QuartzManager.addJob(schedulerFactoryBean, "snortEsToMysqlJob", "snortJob",
                    "snortEsToMysqlTrigger", "snortTrigger", HelloWordJob.class, "0/2 * * * * ? ", params);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
