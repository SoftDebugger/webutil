package com.shunyue.wifidog.web.service.impl;

import com.shunyue.wifidog.dao.TestMapper;
import com.shunyue.wifidog.exception.WifiDefaultException;
import com.shunyue.wifidog.model.Test;
import com.shunyue.wifidog.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by CrazyHarry on 2015/1/1.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Transactional
    @Override
    public void create(Test test) throws WifiDefaultException {
        test.setTest("sdds");
        testMapper.insert(test);
    }
}
