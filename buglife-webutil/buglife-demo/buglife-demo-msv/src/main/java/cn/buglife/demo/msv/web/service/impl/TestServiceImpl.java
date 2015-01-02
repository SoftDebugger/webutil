package cn.buglife.demo.msv.web.service.impl;

import cn.buglife.demo.msv.model.Test;
import cn.buglife.demo.msv.dao.TestMapper;
import cn.buglife.demo.msv.exception.WifiDefaultException;
import cn.buglife.demo.msv.web.service.TestService;
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
