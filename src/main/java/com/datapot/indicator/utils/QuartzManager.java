/**
 * @工程名称：datapot-snort
 * @程序包名：com.datapot.snort.utils
 * @程序类名：QuartzManager.java
 * @创建日期：2018年1月12日
 */
package com.datapot.indicator.utils;

import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @功能说明：定时任务管理类
 * @创建人员：zhenghb
 * @变更记录：<BR> 1、2018年1月12日 zhenghb 新建类
 */
public class QuartzManager {

	/**
	 * @函数名称：addJob
	 * @创建日期：2018年1月12日
	 * @功能说明：添加一个定时任务
	 * @参数说明：
	 * @param schedulerFactoryBean
	 * @param jobName
	 *          任务名
	 * @param jobGroupName
	 *          任务组名
	 * @param triggerName
	 *          触发器名
	 * @param triggerGroupName
	 *          触发器组名
	 * @param jobClass
	 *          任务的类类型 eg:TimedMassJob.class
	 * @param cron
	 *          时间设置 表达式，参考quartz说明文档
	 * @param objects
	 *          可变参数需要进行传参的值
	 * @返回说明：void
	 */
	public static void addJob(SchedulerFactoryBean schedulerFactory, String jobName, String jobGroupName,
                              String triggerName, String triggerGroupName, Class jobClass, String cron, Object... objects) {
		Scheduler scheduler = schedulerFactory.getScheduler();
		try {
			// 任务名，任务组，任务执行类
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
			// 触发器
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					// 该数据可以通过Job中的JobDataMap dataMap =
					// context.getJobDetail().getJobDataMap();来进行参数传递值
					jobDetail.getJobDataMap().put("data" + (i + 1), objects[i]);
				}
			}
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			// 触发器名,触发器组
			triggerBuilder.withIdentity(triggerName, triggerGroupName);
			triggerBuilder.startNow();
			// 触发器时间设定
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
			// 创建Trigger对象
			CronTrigger trigger = (CronTrigger) triggerBuilder.build();
			// 调度容器设置JobDetail和Trigger
			scheduler.scheduleJob(jobDetail, trigger);
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @函数名称：modifyJobTime
	 * @创建日期：2018年1月12日
	 * @功能说明：修改一个任务的触发时间
	 * @参数说明：
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 *          触发器名
	 * @param triggerGroupName
	 *          触发器组名
	 * @param cron
	 *          时间设置，参考quartz说明文档
	 * @返回说明：void
	 */
	public static void modifyJobTime(SchedulerFactoryBean schedulerFactory, String jobName, String jobGroupName,
                                     String triggerName, String triggerGroupName, String cron) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				// 触发器
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
				// 触发器名,触发器组
				triggerBuilder.withIdentity(triggerName, triggerGroupName);
				triggerBuilder.startNow();
				// 触发器时间设定
				triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
				// 创建Trigger对象
				trigger = (CronTrigger) triggerBuilder.build();
				// 方式一 ：修改一个任务的触发时间
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @函数名称：removeJob
	 * @创建日期：2018年1月12日
	 * @功能说明：移除一个任务
	 * @参数说明：
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @返回说明：void
	 */
	public static void removeJob(SchedulerFactoryBean schedulerFactory, String jobName, String jobGroupName,
                                 String triggerName, String triggerGroupName) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();

			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			// 停止触发器
			scheduler.pauseTrigger(triggerKey);
			// 移除触发器
			scheduler.unscheduleJob(triggerKey);
			// 删除任务
			scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @函数名称：startJobs
	 * @创建日期：2018年1月12日
	 * @功能说明：启动所有定时任务
	 * @参数说明：
	 * @返回说明：void
	 */
	public static void startJobs(SchedulerFactoryBean schedulerFactory) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @函数名称：shutdownJobs
	 * @创建日期：2018年1月12日
	 * @功能说明：关闭所有定时任务
	 * @参数说明：
	 * @返回说明：void
	 */
	public static void shutdownJobs(SchedulerFactoryBean schedulerFactory) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			if (!scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
