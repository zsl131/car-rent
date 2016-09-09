package com.ztw.web.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zsl-pc on 2016/9/8.
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {

        return "test/index";
    }
}
