package com.datapot.indicator.service.impl;

import com.datapot.indicator.service.HelloWordService;
import org.springframework.stereotype.Service;


@Service
public class HelloWordImpl implements HelloWordService {
    @Override
    public void helloWord() {
        System.out.println("hello word");
    }
}
