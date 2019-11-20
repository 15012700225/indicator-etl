/**
 * @工程名称：datapot-dao
 * @程序包名：com.datapot.config
 * @程序类名：DynamicDataSourceContextHolder.java
 * @创建日期：2017年8月31日
 */
package com.datapot.indicator.config.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * @功能说明：
 * @创建人员：zhenghb
 * @变更记录：<BR>
 * 1、2017年8月31日 zhenghb 新建类
 */
public class DynamicDataSourceContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static List<String> dataSourceIds = new ArrayList<String>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     * @param dataSourceId
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
