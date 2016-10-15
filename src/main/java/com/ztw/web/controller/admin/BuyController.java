package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.exception.SystemException;
import com.ztw.basic.tools.PageableTools;
import com.ztw.basic.tools.ParamFilterTools;
import com.ztw.car.iservice.IBuyService;
import com.ztw.car.iservice.ICarInfoService;
import com.ztw.car.iservice.ICarService;
import com.ztw.car.model.Buy;
import com.ztw.car.model.Car;
import com.ztw.car.model.CarInfo;
import com.ztw.car.model.Orders;
import com.ztw.car.tools.DateTools;
import com.ztw.deposit.model.Deposit;
import com.ztw.people.iservice.IPeopleService;
import com.ztw.people.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/16 0:10.
 */
@Controller
@RequestMapping(value = "admin/buy")
@AdminAuth(name = "购车订单管理",psn = "应用管理",orderNum = 10,porderNum = 1, pentity = 0)
public class BuyController {

    @Autowired
    private IBuyService buyService;

    @Autowired
    private IPeopleService peopleService;

    @Autowired
    private ICarService carService;

    @Autowired
    private ICarInfoService carInfoService;

    @AdminAuth(name = "购车订单列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Buy> datas = buyService.findAll(new ParamFilterTools<Buy>().buildSpecification(model, request), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/buy/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改订单", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        Buy buy = buyService.findOne(id);
        model.addAttribute("people", peopleService.findByIdentity(buy.getCostumerIdentity())); //获取客户信息
        model.addAttribute("buy", buy);
        return "admin/buy/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Buy buy, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Car car = carService.findById(buy.getCarId());
            CarInfo carInfo = carInfoService.findById(car.getInfoId());
            Buy b = buyService.findOne(id);
//            MyBeanUtils.copyProperties(orders, o, new String[]{"id", "costumerIdentity"});
            People people = peopleService.findByIdentity(buy.getCostumerIdentity());
            //如果客户不存在则先添加
            if(people==null) {
                people = new People();
                people.setStatus("1");
                people.setAddress(buy.getCostumerAddress());
                people.setAge(buy.getCostumerAge());
                people.setCreateDate(new Date());
                people.setIdentity(buy.getCostumerIdentity());
                people.setName(buy.getCostumerName());
                people.setPhone(buy.getCostumerPhone());
                people.setSex(buy.getCostumerSex());

                String idenPic = request.getParameter("idenPic");
                String idenBackPic = request.getParameter("idenBackPic");
                String drivePic = request.getParameter("drivePic");
                people.setIdenPic(idenPic); people.setIdenBackPic(idenBackPic);
                people.setDrivePic(drivePic);
                peopleService.save(people);
            }

            b.setCostumerId(people.getId());

            b.setBrandName(carInfo.getBrandName());
            b.setBrandId(carInfo.getBrandId());
            b.setCarNo(car.getCarNo());
            b.setCarSerial(car.getCarSerial());
            b.setCarType(carInfo.getTypeName());
            b.setTypeId(carInfo.getTypeId());
            b.setInfoId(car.getInfoId());

            buyService.save(b);
        }
        return "redirect:/admin/buy/list";
    }

    @AdminAuth(name="查看订单详情", orderNum=5, type="2")
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable Integer id) {
        Buy buy = buyService.findOne(id);
        model.addAttribute("buy", buy); //订单信息
        model.addAttribute("carInfo", carInfoService.findById(buy.getInfoId())); //车辆信息
        model.addAttribute("people", peopleService.findById(buy.getCostumerId())); //客户信息
        return "admin/buy/show";
    }
}
