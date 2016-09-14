package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.tools.PageableTools;
import com.ztw.deposit.model.Deposit;
import com.ztw.deposit.service.IDepositService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 保证金管理controller
 * Created by 马旭 on 2016/9/8.
 */
@Controller
@RequestMapping(value = "admin/deposit")
@AdminAuth(name = "保证金", orderNum = 10, psn = "系统管理", pentity=0, porderNum=20)
public class DepositController {

    @Autowired
    private IDepositService depositService;

    @AdminAuth(name="保证金管理", orderNum=1, icon="icon-usd")
    @RequestMapping(value="index", method= RequestMethod.GET)
    public String index(HttpServletRequest request, Model model, Integer page,
                        @RequestParam(name = "tenantSfz", defaultValue = "") String tenantSfz) {

        Page<Deposit> datas;
        if(!tenantSfz.equals("")) {
            datas = depositService.pageByTenantSfz(tenantSfz, PageableTools.basicPage(page));
        } else {
            datas = depositService.pageAll(PageableTools.basicPage(page));
        }

        model.addAttribute("datas", datas);
        model.addAttribute("tenantSfz", tenantSfz);
        return "admin/deposit/index";
    }

}
