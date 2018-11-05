package com.chenchuan.admin.test.service.impl;

import com.chenchuan.admin.test.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String testA() {
        return "hellow";
    }
}
