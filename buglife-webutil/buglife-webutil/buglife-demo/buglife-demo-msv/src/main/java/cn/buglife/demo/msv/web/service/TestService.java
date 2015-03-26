package cn.buglife.demo.msv.web.service;

import cn.buglife.demo.msv.exception.WifiDefaultException;
import cn.buglife.demo.msv.model.Test;

/**
 * Created by CrazyHarry on 2015/1/1.
 */
public interface TestService {
    void create(Test test) throws WifiDefaultException;
}
