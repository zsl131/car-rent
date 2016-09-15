package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.tools.ConfigTools;
import com.ztw.basic.tools.MyBeanUtils;
import com.ztw.basic.tools.NormalTools;
import com.ztw.basic.tools.PageableTools;
import com.ztw.car.iservice.ICarTypeService;
import com.ztw.car.model.CarType;
import com.ztw.car.service.CarTypeServiceImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by admin on 2016/9/8.
 */
@Controller
@RequestMapping(value = "/admin/carType")
@AdminAuth(name = "车辆种类管理",psn = "车库管理",orderNum = 3, pentity=0, porderNum=3)
public class CarTypeController {

    private static final String PATH_PRE = "carType/";

    @Autowired
    private ICarTypeService carTypeService;

    @Autowired
    private CarTypeServiceImpl carTypeServiceImpl;

    @Autowired
    private ConfigTools configTools;

    @AdminAuth(name = "种类列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<CarType> datas = carTypeService.pageAll(PageableTools.basicPage(page, "asc", "orderNo"));
        model.addAttribute("datas", datas);
        return "admin/carType/list";
    }

    @Token(flag= Token.READY)
    @AdminAuth(name = "添加种类", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        CarType ct = new CarType();
        model.addAttribute("carType", ct);
        return "admin/carType/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, CarType carType, HttpServletRequest request, @RequestParam("file")MultipartFile[] files) {
        if(TokenTools.isNoRepeat(request)) {
            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                        File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                        carType.setLogo(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
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
            Integer orderNo = carTypeService.findMaxOrderNo();
            carType.setOrderNo((orderNo==null || orderNo<0)?1:orderNo+1);
            carTypeService.save(carType);
        }
        return "redirect:/admin/carType/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改种类", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        CarType ct = carTypeService.findById(id);
        model.addAttribute("carType", ct);
        return "admin/carType/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, CarType carType, HttpServletRequest request, @RequestParam("file")MultipartFile [] files) {
        if(TokenTools.isNoRepeat(request)) {
            CarType ct = carTypeService.findById(id);
            MyBeanUtils.copyProperties(carType, ct, new String[]{"id"});

            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                        File oldFile = new File(configTools.getUploadPath() + ct.getLogo());
                        if(oldFile.exists()) {oldFile.delete();}
                        File outFile = new File(configTools.getUploadPath(PATH_PRE) +"/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                        ct.setLogo(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
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

            carTypeService.save(ct);
        }
        return "redirect:/admin/carType/list";
    }

    @AdminAuth(name="删除品牌", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Integer id) {
        try {
//            peopleService.delete(id);
            CarType cb = carTypeService.findById(id);
            File file = new File(configTools.getUploadPath() + cb.getLogo());
            if(file.exists()) {
                file.delete();
            }
            carTypeService.delete(cb);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    @RequestMapping("updateSort")
    @AdminAuth(name="品牌排序", orderNum=4, type="2")
    public @ResponseBody String updateSort(Integer[] ids) {
        try {
            carTypeServiceImpl.updateSort(ids);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
}

