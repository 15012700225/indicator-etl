/**
 * @工程名称：datapot-parser
 * @程序包名：com.datapot.parser.config
 * @程序类名：SchedulerListener.java
 * @创建日期：2017年9月2日
 */
package com.datapot.detection.config;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @功能说明：
 * @创建人员：zhenghb
 * @变更记录：<BR>
 * 1、2017年9月2日 zhenghb 新建类
 */
@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
  public MyScheduler myScheduler;
	
	@Autowired
  private MyJobFactory myJobFactory;
  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
      try {
          myScheduler.scheduleJobs();
      } catch (SchedulerException e) {
          e.printStackTrace();
      }

  }

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean(){
      SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
      schedulerFactoryBean.setStartupDelay(5);
      schedulerFactoryBean.setJobFactory(myJobFactory);
      return schedulerFactoryBean;
  }

}
