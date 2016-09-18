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
import com.ztw.car.model.Car;
import com.ztw.car.model.CarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zsl-pc on 2016/9/17.
 */
@Controller
@RequestMapping(value = "admin/car")
@AdminAuth(name = "车辆管理",psn = "车库管理",orderNum = 1,porderNum = 2,pentity = 0)
public class CarController {

    @Autowired
    private ICarService carService;

    @Autowired
    private ICarInfoService carInfoService;

    @AdminAuth(name = "车辆列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<Car> datas = carService.findAll(new ParamFilterTools<Car>().buildSpecification(model, request), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/car/list";
    }

    @Token(flag= Token.READY)
    @AdminAuth(name = "添加车辆", orderNum = 2, icon="icon-plus", type = "2")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, Integer infoId, HttpServletRequest request) {
        Car c = new Car();
        model.addAttribute("car", c);
        model.addAttribute("carInfo", carInfoService.findById(infoId));
        return "admin/car/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, Car car, Integer infoId, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Car c = carService.findByCarTypeAndCarNo(car.getCarType(), car.getCarNo());
            if(c!=null) {
                throw new SystemException("车牌号码重复，不能再添加！");
            }
            c = carService.findByEngineNo(car.getEngineNo());
            if(c!=null) {
                throw new SystemException("发动机号重复，不能再添加！");
            }
            c = carService.findByFrameNo(car.getFrameNo());
            if(c!=null) {
                throw new SystemException("车架号重复，不能再添加！");
            }
            CarInfo carInfo = carInfoService.findById(infoId);
            model.addAttribute("carInfo", carInfo);
            car.setInfoId(infoId);
            car.setCarType(carInfo.getClzl()); //车辆种类
            car.setBrandName(carInfo.getBrandName());
            car.setCarNo(car.getCarNo().toUpperCase());
            car.setEngineNo(car.getEngineNo().toUpperCase());
            car.setFrameNo(car.getFrameNo().toUpperCase());
            car.setStatus("1"); car.setCarSerial(carInfo.getPpxl());
            carService.save(car);
        }
        return "redirect:/admin/car/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改车辆", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        Car c = carService.findById(id);
        model.addAttribute("car", c);
        return "admin/car/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, Car car, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            Car c = carService.findById(id);
            MyBeanUtils.copyProperties(car, c, new String[]{"id"});

            carService.save(c);
        }
        return "redirect:/admin/car/list";
    }

    @AdminAuth(name="查看车辆", orderNum=5, type="2")
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable Integer id) {
        model.addAttribute("car", carService.findById(id));
        return "admin/car/show";
    }

    @AdminAuth(name="删除车辆", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Integer id) {
        try {
            carService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}
