/**
 * @工程名称：datapot-dao
 * @程序包名：com.datapot.config
 * @程序类名：DynamicDataSourceAspect.java
 * @创建日期：2017年8月31日
 */
package com.datapot.indicator.etl.config.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @功能说明：切换数据源Advice
 * @创建人员：zhenghb
 * @变更记录：<BR>
 * 1、2017年8月31日 zhenghb 新建类
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        String dsId = ds.name();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
        } else {
            logger.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(ds.name());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        logger.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
