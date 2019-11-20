package com.datapot.detection.service.impl;

import com.datapot.detection.service.HelloWordService;
import org.springframework.stereotype.Service;


@Service
public class HelloWordImpl implements HelloWordService {
    @Override
    public void helloWord() {
        System.out.println("hello word");
    }
}
