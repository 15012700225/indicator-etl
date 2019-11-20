package com.datapot.indicator.etl.timer;

import com.datapot.indicator.etl.bean.AssetsInfoBean;
import com.datapot.indicator.etl.dao.AdLogDao;
import com.datapot.indicator.etl.service.HelloWordService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class HelloWordJob implements Job, Serializable {

    private static final long serialVersionUID = 7837056247639871250L;

    @Autowired
    private HelloWordService helloWordService;
    @Autowired
    private AdLogDao adLogDao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        helloWordService.helloWord();
        //adLogDao.findAll().stream().map(AssetsInfoBean::getAccount_name).forEach(System.out::println);
    }
}
