package com.datapot.detection.dao;

import com.datapot.detection.bean.AssetsInfoBean;
import com.datapot.detection.config.datasource.TargetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @TargetDataSource(name = "om")
    public List<AssetsInfoBean> findAll() {
        RowMapper<AssetsInfoBean> rowMapper = new BeanPropertyRowMapper<AssetsInfoBean>(AssetsInfoBean.class);
        String sql = "SELECT * FROM ad_logon_off WHERE event_ID=4634;";
        return jdbcTemplate.query(sql, rowMapper, new Object[]{});
    }
}
