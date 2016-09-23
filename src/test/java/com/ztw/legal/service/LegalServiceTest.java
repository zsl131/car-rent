package com.ztw.legal.service;

import com.ztw.legal.dto.HpzlInfo;
import com.ztw.legal.dto.SearchInfo;
import com.ztw.legal.dto.TenantInfo;
import com.ztw.legal.model.Legal;
import com.ztw.legal.tools.QueryLegalTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zsl-pc on 2016/9/23.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("zsl")
public class LegalServiceTest {

    @Autowired
    private ILegalService legalService;

    @Autowired
    private LegalServiceImpl legalServiceImpl;

    @Test
    public void testAdd() {
        SearchInfo searchInfo = new SearchInfo(HpzlInfo.小型汽车.getCode(), "云CEX999", "2015-01-01 00:00:00", "2016-09-19 00:00:00", "云南");
        TenantInfo tenantInfo = new TenantInfo("马旭", "532101198802134814", "18087011221", 1);
        legalServiceImpl.searchAndSave(searchInfo, tenantInfo);
    }

    @Test
    public void testList() {
        List<Legal> list = legalService.findByCphmAndCpzl("云CEX999", "02");
        System.out.println("=========="+list.size());
    }

    @Autowired
    QueryLegalTools queryLegalTools;

    @Test
    public void testByTools() {
//        LegalTools.getInstance().queryLegalAndSave("02", "云CEX999");
        queryLegalTools.queryLegalAndSave("02", "云CEX999");
    }
}
