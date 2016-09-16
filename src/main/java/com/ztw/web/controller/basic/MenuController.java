package com.ztw.web.controller.basic;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.iservice.IMenuService;
import com.ztw.basic.auth.model.Menu;
import com.ztw.basic.auth.service.MenuServiceImpl;
import com.ztw.basic.auth.tools.AuthTools;
import com.ztw.basic.tools.BaseSpecification;
import com.ztw.basic.tools.PageableTools;
import com.ztw.basic.tools.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 菜单管理Controller
 * @author zsl-pc 20160511
 *
 */
@Controller
@RequestMapping(value="admin/menu")
@AdminAuth(name = "菜单管理", psn="权限管理", orderNum = 1, pentity=0, porderNum=2)
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private MenuServiceImpl menuServiceImpl;

    /** 列表 */
    @AdminAuth(name = "菜单列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer pid, Integer page, HttpServletRequest request) {
        String treeJson = menuServiceImpl.queryTreeJson(null);
        Page<Menu> datas ;
        if(pid==null || pid<=0) {
            BaseSpecification<Menu> spec = new BaseSpecification<>(new SearchCriteria("pid", "isnull", ""));
            datas = menuService.findAll(Specifications.where(spec), PageableTools.basicPage(page, 15, "asc", "orderNum"));
        } else {
//            datas = menuService.pageAll(pid, PageableTools.basicPage(page, 15, "asc", "orderNum"));
            BaseSpecification<Menu> spec = new BaseSpecification<>(new SearchCriteria("pid", "eq", pid));
            datas = menuService.findAll(Specifications.where(spec), PageableTools.basicPage(page, 15, "asc", "orderNum"));
        }
        model.addAttribute("treeJson", treeJson);
        model.addAttribute("datas", datas);
        return "admin/menu/list";
    }

    @AdminAuth(name="重构菜单", orderNum=3, type="2")
    @RequestMapping(value="rebuildMenus", method=RequestMethod.POST)
    public @ResponseBody String rebuildMenus(Model model, HttpServletRequest request) {
        AuthTools.getInstance().buildSystemMenu(menuServiceImpl);
        return "1";
    }

    @RequestMapping("updateSort")
    @AdminAuth(name="菜单排序", orderNum=4, type="2")
    public @ResponseBody String updateSort(Integer[] ids) {
        try {
            menuServiceImpl.updateSort(ids);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
}