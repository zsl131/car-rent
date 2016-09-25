package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.exception.SystemException;
import com.ztw.basic.tools.ConfigTools;
import com.ztw.basic.tools.MyBeanUtils;
import com.ztw.basic.tools.PageableTools;
import com.ztw.basic.tools.ParamFilterTools;
import com.ztw.people.iservice.IPeopleService;
import com.ztw.people.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by zsl-pc on 2016/9/13.
 */
@Controller
@RequestMapping(value = "admin/people")
@AdminAuth(name = "客户管理", psn="应用管理", orderNum = 1, pentity=0, porderNum=2)
public class PeopleController {

    @Autowired
    private IPeopleService peopleService;

    @Autowired
    private ConfigTools configTools;

    @AdminAuth(name = "客户列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<People> datas = peopleService.findAll(new ParamFilterTools<People>().buildSpecification(model, request), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/people/list";
    }

    @Token(flag= Token.READY)
    @AdminAuth(name = "添加客户", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        People people = new People();
        people.setStatus("1");
        model.addAttribute("people", people);
        return "admin/people/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, People people, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            People p = peopleService.findByIdentity(people.getIdentity());
            if(p!=null) {throw new SystemException("身份证号【"+people.getIdentity()+"】已经存在，不可重复添加！");}
            peopleService.save(people);
        }
        return "redirect:/admin/people/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改客户", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        People p = peopleService.findById(id);
        model.addAttribute("people", p);
        return "admin/people/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, People people, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            People p = peopleService.findById(id);

            if(people.getIdenPic()!=null && p.getIdenPic()!=null && !people.getIdenPic().equalsIgnoreCase(p.getIdenPic())) {
                File f = new File(configTools.getUploadPath("") + p.getIdenPic());
                if(f.exists()) {f.delete();}
            }

            if(people.getIdenBackPic()!=null && p.getIdenBackPic()!=null && !people.getIdenBackPic().equalsIgnoreCase(p.getIdenBackPic())) {
                File f = new File(configTools.getUploadPath("") + p.getIdenBackPic());
                if(f.exists()) {f.delete();}
            }

            if(people.getDrivePic()!=null && p.getDrivePic()!=null && !people.getDrivePic().equalsIgnoreCase(p.getDrivePic())) {
                File f = new File(configTools.getUploadPath("") + p.getDrivePic());
                if(f.exists()) {f.delete();}
            }

            MyBeanUtils.copyProperties(people, p, new String[]{"id"});
            peopleService.save(p);
        }
        return "redirect:/admin/people/list";
    }

    @RequestMapping(value = "show", method = RequestMethod.GET)
    @AdminAuth(name="查询用户", orderNum=6, type="2")
    public String show(Model model, String identity) {
        People p = peopleService.findByIdentity(identity);
        model.addAttribute("people", p);
        return "admin/people/show";
    }

    @AdminAuth(name="删除用户", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Integer id) {
        try {
            peopleService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}
