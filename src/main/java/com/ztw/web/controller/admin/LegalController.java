package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.tools.PageableTools;
import com.ztw.basic.tools.ParamFilterTools;
import com.ztw.legal.model.Legal;
import com.ztw.legal.service.ILegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zsl-pc on 2016/9/23.
 */
@Controller
@RequestMapping(value = "admin/legal")
@AdminAuth(name = "违章管理",psn = "车库管理",orderNum = 5,porderNum = 2,pentity = 0)
public class LegalController {

    @Autowired
    private ILegalService legalService;

    @AdminAuth(name = "违章列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Legal> datas = legalService.findAll(new ParamFilterTools<Legal>().buildSpecification(model, request), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/legal/list";
    }
}
