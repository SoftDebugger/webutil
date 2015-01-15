package com.shunyue.wifidog.web.controller;

import com.shunyue.wifidog.exception.WifiDefaultException;
import com.shunyue.wifidog.model.Test;
import com.shunyue.wifidog.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by CrazyHarry on 2015/1/1.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/create" ,method = RequestMethod.GET)
    public void create(){
        Test test = new Test();
        test.setTest("xxxx");
        try {
            testService.create(test);
        } catch (WifiDefaultException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/vm", method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}
