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
import com.ztw.deposit.model.Deposit;
import com.ztw.deposit.service.IDepositService;
import com.ztw.legal.service.ILegalService;
import com.ztw.people.iservice.IPeopleService;
import com.ztw.people.model.People;
import com.ztw.weixin.tools.WeixinConstant;
import com.ztw.weixin.tools.WeixinXmlUtil;
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

    @Autowired
    private IDepositService depositService;

    @Autowired
    private ILegalService legalService;

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
    public @ResponseBody String updateStatus(@PathVariable Integer id, @PathVariable String status, Float money, String msg) {
        try {
            if(money==null || money<0) {money = 0f;} //如果金额为空或小于0，都将其设置为0
            if(!"0".equalsIgnoreCase(status) && !"1".equalsIgnoreCase(status)) {
                Orders orders = ordersService.findById(id);
                if(orders.getCarId()!=null) {
                    carService.updateStatusByOrders(orders.getCarId(), "1"); //当状态为已归还、已取消、已完结都将车辆设置为在库可出租状态
                }

                if("10".equals(status)) { //如果是完结状态，需要设置压金对象
                    Deposit dep = depositService.findByRentId(id);
                    if(dep!=null) {
                        dep.setForfeitMoney(Double.valueOf(money));
                        dep.setForfeitComments(msg);
                        dep.setStatus("2");
                        dep.setReturnTime(new Date());
                        dep.setReturnMoney(dep.getMoney() - dep.getForfeitMoney() - dep.getLegalMoney()); //实际退还为：总金额-扣除款-违章款
                        depositService.save(dep);
                    }
                }
            }
            Date date = new Date();
            ordersService.updateStatus(id, status, msg, date, DateTools.date2Str(date), date.getTime());
            Orders orders = ordersService.findOne(id);
            if(orders!=null && orders.getOpenid()!=null && !"".equals(orders.getOpenid())) {
                String state = "未知状态";
                if("0".equals(status)) {state = "未提车";}
                else if("1".equals(status)) {state = "已提车";}
                else if("2".equals(status)) {state = "车辆已归还";}
                else if("3".equals(status)) {state = "订单取消";}
                else if("10".equals(status)) {state = "已完结（压金已退）";}
                WeixinXmlUtil.eventRemind("订单状态发生变化", orders.getOpenid(), "订单提醒", "车型："+orders.getBrandName()+orders.getCarSerial()+"\\n订单状态变更为：" + state, WeixinConstant.getInstance().getWeiXinConfig().getUrl() + "/wx/orders");
            }
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

            //添加保证金
            Deposit dep = new Deposit();
            dep.setStatus("1");
            dep.setPhone(orders.getCostumerPhone());
            dep.setForfeitComments(""); dep.setForfeitMoney(0d);
            dep.setLegalMoney(0d); dep.setMoney(Double.valueOf(orders.getDepositMoney()));
            dep.setRentId(orders.getId()); dep.setReturnMoney(0d);
            dep.setStartTime(new Date()); dep.setTenantName(orders.getCostumerName());
            dep.setTenantSfz(orders.getCostumerIdentity());
            depositService.save(dep);
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
            Car car = carService.findById(orders.getCarId());
            if(!"1".equalsIgnoreCase(car.getStatus()) && !car.getId().equals(orders.getCarId())) {throw new SystemException("车辆【"+car.getCarNo()+"】当前状态不是在库的可租车辆");}
            CarInfo carInfo = carInfoService.findById(car.getInfoId());
            Orders o = ordersService.findById(id);
//            MyBeanUtils.copyProperties(orders, o, new String[]{"id", "costumerIdentity"});
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

            o.setCostumerId(people.getId());

            o.setBrandName(carInfo.getBrandName());
            o.setBrandId(carInfo.getBrandId());
            o.setCarNo(car.getCarNo());
            o.setCarSerial(car.getCarSerial());
            o.setCarType(carInfo.getTypeName());
            o.setTypeId(carInfo.getTypeId());
            o.setInfoId(car.getInfoId());
            o.setIsOverdue(0); //未逾期
            o.setLllegalCount(0);
            o.setLllegalMoney(0f);
            o.setLllegalScore(0);
            o.setStatus(orders.getStatus());
            o.setCurPrice(carInfo.getRjj());
            o.setNeedBackDate(DateTools.plusDay(orders.getDays()));
            o.setNeedBackDay(DateTools.plusDay(orders.getDays(), ""));
            o.setNeedBackLong(DateTools.plusDayByLong(orders.getDays()));

            ordersService.save(o);

            Deposit dep = depositService.findByRentId(o.getId());
            if(dep!=null) {
                dep.setStatus("1");
                dep.setPhone(orders.getCostumerPhone());
                dep.setForfeitComments(""); dep.setForfeitMoney(0d);
                dep.setLegalMoney(0d); dep.setMoney(Double.valueOf(orders.getDepositMoney()));
                dep.setRentId(orders.getId()); dep.setReturnMoney(0d);
                dep.setStartTime(new Date()); dep.setTenantName(orders.getCostumerName());
                dep.setTenantSfz(orders.getCostumerIdentity());
                depositService.save(dep);
            }
        }
        return "redirect:/admin/orders/list";
    }

    @AdminAuth(name="查看订单详情", orderNum=5, type="2")
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable Integer id) {
        Orders orders = ordersService.findById(id);
        model.addAttribute("orders", orders); //订单信息
        model.addAttribute("carInfo", carInfoService.findById(orders.getInfoId())); //车辆信息
        model.addAttribute("deposit", depositService.findByRentId(id));  //压金信息
        model.addAttribute("legalList", legalService.getLegalByRentId(id)); //违章信息
        model.addAttribute("people", peopleService.findById(orders.getCostumerId())); //客户信息
        return "admin/orders/show";
    }
}
