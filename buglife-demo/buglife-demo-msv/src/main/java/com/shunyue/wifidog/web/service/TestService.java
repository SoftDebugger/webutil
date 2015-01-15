package com.shunyue.wifidog.web.service;

import com.shunyue.wifidog.exception.WifiDefaultException;
import com.shunyue.wifidog.model.Test;

/**
 * Created by CrazyHarry on 2015/1/1.
 */
public interface TestService {
    void create(Test test) throws WifiDefaultException;
}
