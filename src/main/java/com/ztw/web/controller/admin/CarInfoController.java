package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.tools.*;
import com.ztw.car.iservice.ICarBrandService;
import com.ztw.car.iservice.ICarInfoService;
import com.ztw.car.iservice.ICarTypeService;
import com.ztw.car.model.CarInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zsl-pc on 2016/9/15.
 */
@Controller
@RequestMapping(value ="/admin/carInfo")
@AdminAuth(name = "车辆信息管理",psn = "车库管理",orderNum = 1,porderNum = 2,pentity = 0)
public class CarInfoController {

    private static final String PATH_PRE = "car/";

    @Autowired
    private ICarTypeService carTypeService;

    @Autowired
    private ICarInfoService carInfoService;

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private ConfigTools configTools;

    @AdminAuth(name = "车辆信息列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<CarInfo> datas = carInfoService.findAll(new ParamFilterTools<CarInfo>().buildSpecification(model, request), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/carInfo/list";
    }

    @Token(flag= Token.READY)
    @AdminAuth(name = "添加车辆信息", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        CarInfo ci = new CarInfo();
        model.addAttribute("carInfo", ci);
        model.addAttribute("brandList", carBrandService.findAll());
        model.addAttribute("typeList", carTypeService.findAll());
        return "admin/carInfo/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, CarInfo carInfo, HttpServletRequest request, @RequestParam("file")MultipartFile [] files) {
        if(TokenTools.isNoRepeat(request)) {
            carInfo.setBrandName(carBrandService.findById(carInfo.getBrandId()).getName());
            carInfo.setTypeName(carTypeService.findById(carInfo.getTypeId()).getName());

            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    for(int i=0; i< files.length; i++) {
                        MultipartFile f = files[i];
                        String fileName = f.getOriginalFilename();
                        if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                            File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                            if(i==0) {
                                carInfo.setTpdz1(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                            } else if(i==1) {
                                carInfo.setTpdz2(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                            } else if(i==2) {
                                carInfo.setTpdz3(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                            } else if(i==3) {
                                carInfo.setTpdz4(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                            }
                            FileUtils.copyInputStreamToFile(f.getInputStream(), outFile);
                        }
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

            carInfo.setClzl("02");
            carInfoService.save(carInfo);
        }
        return "redirect:/admin/carInfo/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改车辆信息", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        CarInfo ci = carInfoService.findById(id);
        model.addAttribute("carInfo", ci);
        model.addAttribute("brandList", carBrandService.findAll());
        model.addAttribute("typeList", carTypeService.findAll());
        return "admin/carInfo/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, CarInfo carInfo, HttpServletRequest request, @RequestParam("file")MultipartFile[] files) {
        if(TokenTools.isNoRepeat(request)) {
            CarInfo ci = carInfoService.findById(id);
            MyBeanUtils.copyProperties(carInfo, ci, new String[]{"id", "clzl"});
//            carInfo.setBrandName(carBrandService.findById(carInfo.getBrandId()).getName());
//            carInfo.setTypeName(carTypeService.findById(carInfo.getTypeId()).getName());

            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    for(int i=0; i< files.length; i++) {
                        MultipartFile f = files[i];
                        String fileName = f.getOriginalFilename();
                        if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                            File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                            if(i==0) {
                                File oldFile = new File(configTools.getUploadPath() + ci.getTpdz1());
                                if(oldFile.exists()) {oldFile.delete();}
                                ci.setTpdz1(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                            } else if(i==1) {
                                File oldFile = new File(configTools.getUploadPath() + ci.getTpdz2());
                                if(oldFile.exists()) {oldFile.delete();}
                                ci.setTpdz2(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                            } else if(i==2) {
                                File oldFile = new File(configTools.getUploadPath() + ci.getTpdz3());
                                if(oldFile.exists()) {oldFile.delete();}
                                ci.setTpdz3(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                            } else if(i==3) {
                                File oldFile = new File(configTools.getUploadPath() + ci.getTpdz4());
                                if(oldFile.exists()) {oldFile.delete();}
                                ci.setTpdz4(outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                            }
                            FileUtils.copyInputStreamToFile(f.getInputStream(), outFile);
                        }
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

            carInfoService.save(ci);
        }
        return "redirect:/admin/carInfo/list";
    }

    @AdminAuth(name="查看车辆信息", orderNum=5, type="2")
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable Integer id) {
        model.addAttribute("carInfo", carInfoService.findById(id));
        return "admin/carInfo/show";
    }

    /*@AdminAuth(name="删除车辆信息", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Integer id) {
        try {
            carInfoService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }*/
}
