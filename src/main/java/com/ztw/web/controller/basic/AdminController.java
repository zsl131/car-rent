package com.ztw.web.controller.basic;

import com.ztw.basic.auth.dto.AuthToken;
import com.ztw.basic.auth.iservice.IUserService;
import com.ztw.basic.auth.model.User;
import com.ztw.basic.auth.tools.SecurityUtil;
import com.ztw.car.iservice.ICarBrandService;
import com.ztw.car.iservice.ICarService;
import com.ztw.car.iservice.ICarTypeService;
import com.ztw.car.iservice.IOrdersService;
import com.ztw.people.iservice.IPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zsl-pc on 2016/9/7.
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICarService carService;

    @Autowired
    private ICarTypeService carTypeService;

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private IPeopleService peopleService;

    @Autowired
    private IOrdersService ordersService;

    /** 后台首页 */
    @RequestMapping(value={"", "/"}, method= RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {

        model.addAttribute("peopleCount", peopleService.queryCount()); //用户数量
        model.addAttribute("carCount", carService.queryCount()); //车辆数量
        model.addAttribute("carBrandCount", carBrandService.queryCount()); //车辆品牌数量
        model.addAttribute("carTypeCount", carTypeService.queryCount()); //车辆种类数量
        return "admin/index";
    }

    /** 修改密码 */
    @RequestMapping(value="updatePwd")
    public String updatePwd(Model model, Integer flag, String oldPwd, String password, String nickname, HttpServletRequest request) {
        String method = request.getMethod(); //获取请求方式，GET、POST
        if("get".equalsIgnoreCase(method)) {
            model.addAttribute("flag", flag);
            return "admin/updatePwd";
        } else if("post".equalsIgnoreCase(method)) {
            AuthToken at = (AuthToken) request.getSession().getAttribute(AuthToken.SESSION_NAME);
            User user = at.getUser();
            try {
                if(password!=null && !"".equals(password)) { //如果没有输入密码，则不修改
                    if(!SecurityUtil.md5(user.getUsername(), oldPwd).equals(user.getPassword())) {
                        model.addAttribute("errorMsg", "原始密码输入错误");
                        return "admin/updatePwd";
                    }
                    user.setPassword(SecurityUtil.md5(user.getUsername(), password));
                }
                user.setNickname(nickname);
                userService.save(user);
                return "redirect:/admin/updatePwd?flag=1";
            } catch (Exception e) {
                //e.printStackTrace();
                return "redirect:/admin/updatePwd?flag=2";
            }
        }
        return "redirect:/admin/updatePwd";
    }
}
