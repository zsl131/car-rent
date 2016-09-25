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
import org.springframework.web.bind.annotation.*;

/**
 * 保证金管理controller
 * Created by 马旭 on 2016/9/8.
 */
@Controller
@RequestMapping(value = "admin/deposit")
@AdminAuth(name = "保证金", orderNum = 2, psn = "应用管理", pentity=0, porderNum=2)
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

    @AdminAuth(name="返还保证金", orderNum=2, icon="icon-pencil", type="2")
    @RequestMapping(value = "/updateSt/{id}", method = RequestMethod.POST)
    public @ResponseBody String updateStatus(@PathVariable Integer id) {
        try {
            depositService.updateStatus(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * 修改其他扣除金额
     * @param id
     * @param forfeitMoney
     * @return
     */
    @AdminAuth(name="其他扣除金额", orderNum = 3, type = "2")
    @RequestMapping(value = "/updateFM", method = RequestMethod.POST)
    public @ResponseBody String updateForfeitM(@RequestParam(value = "value") String forfeitMoney, @RequestParam(value = "pk") Integer id) {
        Double dMoney;
        try {
            if(forfeitMoney.equals("")) {
                dMoney = 0.0;
            } else {
                dMoney = Double.parseDouble(forfeitMoney);
            }
        } catch (NumberFormatException e) {
            return "0";
        }

        Deposit existDeposit = depositService.findById(id);
        Double existForfeitMoney = existDeposit.getForfeitMoney();
        if(existForfeitMoney == null) {
            existForfeitMoney = 0.0;
        }
        Double returnMoney = existDeposit.getReturnMoney() + existForfeitMoney - dMoney;

        try {
            depositService.updateFM(id, dMoney, returnMoney);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * 修改其他扣除金额备注
     * @param id
     * @param forfeitComments
     * @return
     */
    @AdminAuth(name="其他扣除金额备注", orderNum = 4, type = "2")
    @RequestMapping(value = "/updateFC", method = RequestMethod.POST)
    public @ResponseBody String updateForfeitC(@RequestParam(value = "pk") Integer id, @RequestParam(value = "value") String forfeitComments) {
        try {
            depositService.updateFC(id, forfeitComments);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

}
