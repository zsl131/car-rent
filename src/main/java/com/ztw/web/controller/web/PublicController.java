package com.ztw.web.controller.web;

import com.ztw.basic.tools.ConfigTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 公共的Controller
 * Created by zsl-pc on 2016/9/13.
 */
@Controller
@RequestMapping(value = "public")
public class PublicController {

    @Autowired
    ConfigTools configTools;

    /**
     * 显示图片信息
     * 将系统中的图片转换成Base64来显示
     * @param fileName
     * @return
     */
    @RequestMapping(value = "showImg")
    public @ResponseBody String showImg(String fileName) {
        File file = new File(configTools.getUploadPath()+fileName);
        if(file.exists()) {
            try {
                byte [] data = null;
                InputStream is = new FileInputStream(file);
                data = new byte[is.available()];
                is.read(data);
                is.close();

                BASE64Encoder encoder = new BASE64Encoder();
                String res = encoder.encode(data);
                return res;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
