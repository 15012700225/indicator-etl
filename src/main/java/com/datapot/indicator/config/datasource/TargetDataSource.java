/**
 * @工程名称：datapot-dao
 * @程序包名：com.datapot.config
 * @程序类名：TargetDataSource.java
 * @创建日期：2017年8月31日
 */
package com.datapot.indicator.config.datasource;

import java.lang.annotation.*;

/**
 * @功能说明：在方法上使用，用于指定使用哪个数据源
 * @创建人员：zhenghb
 * @变更记录：<BR>
 * 1、2017年8月31日 zhenghb 新建类
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
  String name();
}
