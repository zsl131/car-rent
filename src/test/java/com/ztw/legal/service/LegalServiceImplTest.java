package com.ztw.legal.service;

import com.ztw.deposit.service.IDepositService;
import com.ztw.legal.dto.HpzlInfo;
import com.ztw.legal.dto.SearchInfo;
import com.ztw.legal.dto.TenantInfo;
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

    @Autowired
    private IDepositService depositService;

    @Test
    public void test() {
        SearchInfo searchInfo = new SearchInfo(HpzlInfo.小型汽车.getCode(), "云CEX999", "2015-01-01 00:00:00", "2016-09-19 00:00:00", "云南");
        TenantInfo tenantInfo = new TenantInfo("马旭", "532101198802134814", "18087011221", 1);
        legalServiceImpl.searchAndSave(searchInfo, tenantInfo);
    }

}