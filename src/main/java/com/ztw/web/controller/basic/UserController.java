package com.ztw.web.controller.basic;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.iservice.IRoleService;
import com.ztw.basic.auth.iservice.IUserService;
import com.ztw.basic.auth.model.Role;
import com.ztw.basic.auth.model.User;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.tools.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/6.
 */
@Controller
@RequestMapping(value="admin/user")
@AdminAuth(name = "用户管理", psn="权限管理", orderNum = 3, pentity=0, porderNum=2)
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    /** 列表 */
    @AdminAuth(name = "用户列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method=RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<User> datas = userService.pageAll(PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/user/list";
    }

    @AdminAuth(name="用户授权", orderNum=5, type="2")
    @RequestMapping(value="roles/{id}", method=RequestMethod.GET)
    public String roles(Model model, @PathVariable Integer id, HttpServletRequest request) {
        model.addAttribute("user", userService.findById(id)); //获取当前用户
        List<Integer> userRoleIds = userService.listUserRoleIds(id);
        StringBuffer sb = new StringBuffer();
        for(Integer rid : userRoleIds) {sb.append(rid).append(",");}
        sb.append("0");
        List<Role> roleList = roleService.findAll(); //获取所有角色
        model.addAttribute("roleList", roleList);
        model.addAttribute("roleIds", sb.toString()); //获取当前用户所拥有的角色Id
        return "admin/user/roles";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name = "添加用户", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        User user = new User();
        user.setStatus(1);
        model.addAttribute("user", user);
        return "admin/user/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, User user, HttpServletRequest request) {
//		Boolean isRepeat = (Boolean) request.getAttribute("isRepeat");
//		if(isRepeat==null || !isRepeat) { //不是重复提交
        if(TokenTools.isNoRepeat(request)) {
            user.setStatus("on".equalsIgnoreCase(request.getParameter("statusBox"))?1:0);
            userService.save(user);
        }
        return "redirect:/admin/user/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改用户", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        User u = userService.findById(id);
        model.addAttribute("user", u);
        return "admin/user/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, User user, HttpServletRequest request) {
//		Boolean isRepeat = (Boolean) request.getAttribute("isRepeat");
//		if(isRepeat==null || !isRepeat) {
        if(TokenTools.isNoRepeat(request)) {
            User u = userService.findById(id);
            u.setIsAdmin(user.getIsAdmin());
//			u.setStatus(user.getStatus());
            u.setStatus("on".equalsIgnoreCase(request.getParameter("statusBox"))?1:0);
            u.setNickname(user.getNickname());
            userService.save(u);
        }
        return "redirect:/admin/user/list";
    }

    @AdminAuth(name="删除用户", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Integer id) {
        try {
            userService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}
