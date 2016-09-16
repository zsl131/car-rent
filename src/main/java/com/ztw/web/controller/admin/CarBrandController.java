package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.tools.*;
import com.ztw.car.iservice.ICarBrandService;
import com.ztw.car.model.CarBrand;
import com.ztw.car.service.CarBrandServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zsl-pc on 2016/9/14.
 */
@Controller
@RequestMapping(value = "admin/carBrand")
@AdminAuth(name = "车辆品牌管理", psn="车库管理", orderNum = 2, pentity=0, porderNum=3)
public class CarBrandController {

    private static final String PATH_PRE = "carBrand/";

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private CarBrandServiceImpl carBrandServiceImpl;

    @Autowired
    private ConfigTools configTools;

    @AdminAuth(name = "品牌列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<CarBrand> datas = carBrandService.findAll(new ParamFilterTools<CarBrand>().buildSpecification(model, request), PageableTools.basicPage(page, "asc", "orderNo"));
        model.addAttribute("datas", datas);
        return "admin/carBrand/list";
    }

    @Token(flag= Token.READY)
    @AdminAuth(name = "添加品牌", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        CarBrand cb = new CarBrand();
        model.addAttribute("carBrand", cb);
        return "admin/carBrand/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, CarBrand carBrand, HttpServletRequest request, @RequestParam("file")MultipartFile [] files) {
        if(TokenTools.isNoRepeat(request)) {
            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                        File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                        carBrand.setLogo(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(bw!=null) {bw.close();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Integer orderNo = carBrandService.findMaxOrderNo();
            carBrand.setOrderNo((orderNo==null || orderNo<0)?1:orderNo+1);
            carBrandService.save(carBrand);
        }
        return "redirect:/admin/carBrand/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改品牌", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        CarBrand cb = carBrandService.findById(id);
        model.addAttribute("carBrand", cb);
        return "admin/carBrand/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, CarBrand carBrand, HttpServletRequest request, @RequestParam("file")MultipartFile [] files) {
        if(TokenTools.isNoRepeat(request)) {
            CarBrand cb = carBrandService.findById(id);
            MyBeanUtils.copyProperties(carBrand, cb, new String[]{"id"});

            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                        File oldFile = new File(configTools.getUploadPath() + cb.getLogo());
                        if(oldFile.exists()) {oldFile.delete();}
                        File outFile = new File(configTools.getUploadPath(PATH_PRE) +"/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                        cb.setLogo(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(bw!=null) {bw.close();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            carBrandService.save(cb);
        }
        return "redirect:/admin/carBrand/list";
    }

    @AdminAuth(name="删除品牌", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Integer id) {
        try {
//            peopleService.delete(id);
            CarBrand cb = carBrandService.findById(id);
            File file = new File(configTools.getUploadPath() + cb.getLogo());
            if(file.exists()) {
                file.delete();
            }
            carBrandService.delete(cb);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    @RequestMapping("updateSort")
    @AdminAuth(name="品牌排序", orderNum=4, type="2")
    public @ResponseBody String updateSort(Integer[] ids) {
        try {
            carBrandServiceImpl.updateSort(ids);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
}
