package com.ztw.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.tools.PageableTools;
import com.ztw.basic.tools.ParamFilterTools;
import com.ztw.weixin.iservice.IWeiXinConfigService;
import com.ztw.weixin.iservice.IWeiXinMenuService;
import com.ztw.weixin.iservice.IWeixinService;
import com.ztw.weixin.model.WeiXin;
import com.ztw.weixin.model.WeiXinConfig;
import com.ztw.weixin.model.WeiXinMenu;
import com.ztw.weixin.util.BeanFactoryContext;
import com.ztw.weixin.util.RefreshAccessToken;
import com.ztw.weixin.util.WeiXinMenuTool;
import com.ztw.weixin.util.WeixinUtil;
import com.ztw.weixin.vo.Message;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by admin on 2016/9/18.
 */
@Controller
@RequestMapping(value = "/admin/weixin")
@AdminAuth(name = "微信管理",psn = "微信管理",orderNum = 1,pentity = 0,porderNum = 1)
public class WeiXinController {

    @Autowired
    private IWeixinService weixinService;

    @Autowired
    private IWeiXinConfigService weiXinConfigService;

    @Autowired
    private IWeiXinMenuService weiXinMenuService;


    @AdminAuth(name = "微信列表",orderNum = 2,icon = "icon-list")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request,Integer page){
        Page<WeiXin> datas = weixinService.findAll(new ParamFilterTools<WeiXin>().buildSpecification(model,request), PageableTools.basicPage(page));
        model.addAttribute("datas",datas);
        return "/admin/weixin/list";
    }

    @AdminAuth(name = "微信配置", orderNum = 3,icon = "icon-plus")
    @RequestMapping(value = "config",method = RequestMethod.GET)
    public String weixinConfig(Model model,HttpServletRequest request){
        boolean flag = false;
        if(BeanFactoryContext.getAccessToken()==null) flag =true;
        System.out.println("========================================="+BeanFactoryContext.getAccessToken());
        WeiXinConfig weiXinConfig = weiXinConfigService.findOne(1);
        if(weiXinConfig==null) weiXinConfig = new WeiXinConfig();
        model.addAttribute("weiXinConfig",weiXinConfig);
        model.addAttribute("flag",flag);
        return "/admin/weixin/config";
    }

    @RequestMapping(value = "config",method = RequestMethod.POST)
    public String weixinConfig(Model model,WeiXinConfig weiXinConfig){
        boolean flag = false;
        if(BeanFactoryContext.getAccessToken()==null) flag =true;
        weiXinConfig.setId(1);
        weiXinConfigService.save(weiXinConfig);
        model.addAttribute("flag",flag);
        return "/admin/weixin/config";
    }


    @AdminAuth(name = "获取Token",orderNum = 8,type = "2")
    @RequestMapping(value = "getAccessToken",method = RequestMethod.POST)
    public @ResponseBody String getAccessToken(Model model){
        String accessToken = BeanFactoryContext.getAccessToken();
        //启动定时类获取token
        if(accessToken==null||"".equals(accessToken))new RefreshAccessToken(7200,weiXinConfigService);
        return "success";
    }


    @AdminAuth(name = "微信菜单列表",orderNum = 4,icon = "icon-list")
    @RequestMapping(value = "menuList",method = RequestMethod.GET)
    public String menuList(Model model,Integer page,Integer pid,HttpServletRequest request){
        Page<WeiXinMenu> datas = null;
        if(pid==null||"".equals(pid)){
            datas = weiXinMenuService.findAllPidIsNull(PageableTools.basicPage(page));
        }else{
            datas = weiXinMenuService.findByPid(pid,PageableTools.basicPage(page));
        }
        model.addAttribute("datas",datas);
        return "/admin/weixin/menuList";
    }

    @AdminAuth(name = "添加微信菜单",orderNum = 5,icon = "icon-plus" )
    @RequestMapping(value = "addMenu",method = RequestMethod.GET)
    @Token(flag = Token.READY)
    public String addWeiXinMenu(Model model,Integer pid,HttpServletRequest request){
        model.addAttribute("weiXinMenu",new WeiXinMenu( ));
        model.addAttribute("pid",pid);
        return "/admin/weixin/addMenu";
    }



    @RequestMapping(value = "addMenu",method = RequestMethod.POST)
    @Token(flag = Token.CHECK)
    public String addWeiXinMenu(Model model, WeiXinMenu weiXinMenu,Integer pid, HttpServletRequest request){
        if(TokenTools.isNoRepeat(request)){
            if(pid==null||"".equals(pid)) weiXinMenu.setPid(pid);
            weiXinMenu.setCreateDate(new Date());
            weiXinMenuService.save(weiXinMenu);
        }
        return "redirect:/admin/weixin/menuList";
    }

    /**
     * 生成微信菜单
     */
    @AdminAuth(name = "生成微信菜单",orderNum = 6,icon = "",type = "2")
    @RequestMapping(value = "createMenu",method = RequestMethod.POST)
    public @ResponseBody String createWeiXinMenu(Model model,HttpServletRequest request){
        String result = WeiXinMenuTool.createWeiXinMenuJson(
                BeanFactoryContext.getAccessToken(), WeixinUtil.createWeiXinMenu(weiXinMenuService));
        Message meg = JSON.parseObject(result,Message.class);
        if(meg.getErrcode().equals("ok")){
            return "success";
        }else{
            return "错误代码："+meg.getErrcode();
        }
    }

    @AdminAuth(name = "更新微信菜单",orderNum = 7,icon = "",type = "2")
    @RequestMapping(value = "updateWeiXinMenu",method = RequestMethod.GET)
    public String updateWeiXinMenu(Model model,Integer id,HttpServletRequest request){
        WeiXinMenu weiXinMenu = weiXinMenuService.findOne(id);
        model.addAttribute("weiXinMenu",weiXinMenu);
        return "/admin/weixin/updateMenu";
    }

    @RequestMapping(value = "updateWeiXinMenu",method = RequestMethod.POST)
    public String updateWeiXinMenu(Model model,HttpServletRequest request){

        return "";
    }

}
