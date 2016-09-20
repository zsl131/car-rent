package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.tools.MyBeanUtils;
import com.ztw.basic.tools.PageableTools;
import com.ztw.basic.tools.ParamFilterTools;
import com.ztw.car.iservice.ICarInfoService;
import com.ztw.car.iservice.ICarService;
import com.ztw.car.iservice.IOrdersService;
import com.ztw.car.model.Car;
import com.ztw.car.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/18.
 */
@Controller
@RequestMapping(value = "admin/orders")
@AdminAuth(name = "订单管理",psn = "应用管理",orderNum = 1,porderNum = 1, pentity = 0)
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private ICarService carService;

    @Autowired
    private ICarInfoService carInfoService;

    /** 通过条件获取车辆信息 */
    @RequestMapping(value = "listCars", method = RequestMethod.GET)
    @AdminAuth(name = "查询车辆信息", orderNum = 1, icon="icon-list", type = "2")
    public String listCars(Model model, String params) {
        List<Car> list = carService.listCars(params);
        model.addAttribute("carList", list);
        return "admin/orders/listCars";
    }

    @AdminAuth(name = "订单列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Orders> datas = ordersService.findAll(new ParamFilterTools<Orders>().buildSpecification(model, request), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/orders/list";
    }

    @Token(flag= Token.READY)
    @AdminAuth(name = "添加订单", orderNum = 2, icon="icon-plus", type = "2")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        Orders o = new Orders();
        o.setStatus("0");
        model.addAttribute("orders", o);
        return "admin/orders/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, Orders orders, Integer infoId, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            ordersService.save(orders);
        }
        return "redirect:/admin/orders/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改订单", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        Orders orders = ordersService.findById(id);
        model.addAttribute("orders", orders);
        return "admin/orders/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Orders orders, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Orders o = ordersService.findById(id);
            MyBeanUtils.copyProperties(orders, o, new String[]{"id"});

            ordersService.save(o);
        }
        return "redirect:/admin/orders/list";
    }

    @AdminAuth(name="查看订单详情", orderNum=5, type="2")
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable Integer id) {
        model.addAttribute("orders", ordersService.findById(id));
        return "admin/orders/show";
    }
}
