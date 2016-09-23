package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.exception.SystemException;
import com.ztw.basic.tools.MyBeanUtils;
import com.ztw.basic.tools.PageableTools;
import com.ztw.basic.tools.ParamFilterTools;
import com.ztw.car.iservice.ICarInfoService;
import com.ztw.car.iservice.ICarService;
import com.ztw.car.iservice.IOrdersService;
import com.ztw.car.model.Car;
import com.ztw.car.model.CarBrand;
import com.ztw.car.model.CarInfo;
import com.ztw.car.model.Orders;
import com.ztw.car.tools.DateTools;
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
import java.util.Date;
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

    @Autowired
    private IPeopleService peopleService;

    @RequestMapping(value = "queryPeople", method = RequestMethod.POST)
    @AdminAuth(name = "查询客户信息", orderNum = 7, icon="icon-list", type = "2")
    public @ResponseBody
    People queryPeople(String identity) {
        return peopleService.findByIdentity(identity);
    }

    @RequestMapping(value = "queryCarInfo", method = RequestMethod.POST)
    @AdminAuth(name = "查询单张车辆信息", orderNum = 8, icon="icon-list", type = "2")
    public @ResponseBody
    CarInfo queryCarInfo(Integer id) {
        return carInfoService.findById(id);
    }

    /** 通过条件获取车辆信息 */
    @RequestMapping(value = "listCars", method = RequestMethod.GET)
    @AdminAuth(name = "查询车辆信息", orderNum = 1, icon="icon-list", type = "2")
    public String listCars(Model model, String params) {
        List<Car> list = carService.listCars(params);
        model.addAttribute("carList", list);
        return "admin/orders/listCars";
    }

    @AdminAuth(name = "修改订单状态", orderNum = 8, type = "2")
    @RequestMapping(value = "updateStatus/{id}/{status}", method = RequestMethod.POST)
    public @ResponseBody String updateStatus(@PathVariable Integer id, @PathVariable String status, String msg) {
        try {
            if(!"0".equalsIgnoreCase(status) && !"1".equalsIgnoreCase(status)) {
                Orders orders = ordersService.findById(id);
                carService.updateStatusByOrders(orders.getCarId(), "1"); //当状态为已归还、已取消、已完结都将车辆设置为在库可出租状态
            }
            ordersService.updateStatus(id, status, msg);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
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
    public String add(Model model, Orders orders, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Car car = carService.findById(orders.getCarId());
            if(!"1".equalsIgnoreCase(car.getStatus())) {throw new SystemException("车辆【"+car.getCarNo()+"】当前状态不是在库的可租车辆");}
            CarInfo carInfo = carInfoService.findById(car.getInfoId());

            People people = peopleService.findByIdentity(orders.getCostumerIdentity());
            //如果客户不存在则先添加
            if(people==null) {
                people = new People();
                people.setStatus("1");
                people.setAddress(orders.getCostumerAddress());
                people.setAge(orders.getCostumerAge());
                people.setCreateDate(new Date());
                people.setIdentity(orders.getCostumerIdentity());
                people.setName(orders.getCostumerName());
                people.setPhone(orders.getCostumerPhone());
                people.setSex(orders.getCostumerSex());

                String idenPic = request.getParameter("idenPic");
                String idenBackPic = request.getParameter("idenBackPic");
                String drivePic = request.getParameter("drivePic");
                people.setIdenPic(idenPic); people.setIdenBackPic(idenBackPic);
                people.setDrivePic(drivePic);
                peopleService.save(people);
            }

            orders.setCostumerId(people.getId());

            orders.setBrandName(carInfo.getBrandName());
            orders.setBrandId(carInfo.getBrandId());
            orders.setCarNo(car.getCarNo());
            orders.setCarSerial(car.getCarSerial());
            orders.setCarType(carInfo.getTypeName());
            orders.setTypeId(carInfo.getTypeId());
            orders.setCreateDate(new Date());
            orders.setCreateDateLong(System.currentTimeMillis());
            orders.setInfoId(car.getInfoId());
            orders.setIsOverdue(0); //未逾期
            orders.setLllegalCount(0);
            orders.setLllegalMoney(0f);
            orders.setLllegalScore(0);
            orders.setCurPrice(carInfo.getRjj());
            orders.setType("0"); //管理员下单
            orders.setNeedBackDate(DateTools.plusDay(orders.getDays()));
            orders.setNeedBackDay(DateTools.plusDay(orders.getDays(), ""));
            orders.setNeedBackLong(DateTools.plusDayByLong(orders.getDays()));

            car.setStatus("2"); //修改车辆为已出租
            carService.save(car);

            ordersService.save(orders);
        }
        return "redirect:/admin/orders/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改订单", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        Orders orders = ordersService.findById(id);
        model.addAttribute("people", peopleService.findByIdentity(orders.getCostumerIdentity())); //获取客户信息
        model.addAttribute("orders", orders);
        return "admin/orders/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Orders orders, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Orders o = ordersService.findById(id);
            MyBeanUtils.copyProperties(orders, o, new String[]{"id", "costumerIdentity"});

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
