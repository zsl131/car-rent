package com.ztw.legal.service;

import com.ztw.legal.model.HpzlInfo;
import com.ztw.legal.model.SearchInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 马旭 on 2016/9/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("mx")
public class LegalServiceImplTest {

    @Autowired
    private LegalServiceImpl legalServiceImpl;

    @Test
    public void test() {
        SearchInfo searchInfo = new SearchInfo();
        searchInfo.setHpzl(HpzlInfo.小型汽车.getCode());
        searchInfo.setHphm("云CEX999");
        searchInfo.setJssj("2016-09-19 00:00:00");
        searchInfo.setKssj("2015-01-01 00:00:00");
        searchInfo.setWzsf("云南");
        legalServiceImpl.searchAndSave(searchInfo, "马旭", "532101198802134814", "18087011221", 1);
    }

}