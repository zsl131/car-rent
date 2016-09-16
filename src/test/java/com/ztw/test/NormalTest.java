package com.ztw.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zsl-pc on 2016/9/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class NormalTest {

    @Test
    public void test() {
        String name = "filter_zsl_filter_zsl";
        System.out.println(name.replaceFirst("filter_", ""));
    }
}
