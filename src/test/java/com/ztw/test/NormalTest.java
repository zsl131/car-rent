package com.ztw.test;

import com.ztw.car.tools.DateTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void test2() {
        List<Long> timeList = new ArrayList<>();
        timeList.add(123l);
        timeList.add(456l);

        System.out.println(timeList.toArray(new Long[timeList.size()])[0]);
    }

    @Test
    public void test3() throws Exception {
        String d = "2016-09-16";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
        boolean res = DateTools.isOverdue(date, 10);
        System.out.println("是否逾期========"+res);
    }
}
