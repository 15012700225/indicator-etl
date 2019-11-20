/**
 * @工程名称：datapot-dao
 * @程序包名：com.datapot.config
 * @程序类名：DynamicDataSource.java
 * @创建日期：2017年8月31日
 */
package com.datapot.indicator.config.datasource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @功能说明：动态数据源
 * @创建人员：zhenghb
 * @变更记录：<BR>
 * 1、2017年8月31日 zhenghb 新建类
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSourceType();
	}

}
