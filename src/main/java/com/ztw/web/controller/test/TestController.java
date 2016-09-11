package com.ztw.web.controller.test;

import com.ztw.basic.tools.ConfigTools;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by zsl-pc on 2016/9/8.
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    ConfigTools configTools;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        System.out.println("=============="+configTools.getUploadPath());
        return "test/index";
    }

    @RequestMapping(value = "uploadFiles", method = RequestMethod.POST)
    public @ResponseBody String uploadFiles(@RequestParam("file")MultipartFile [] files) {
        String fileName = null;
        String msg = "";
        if(files!=null && files.length>0) {
            BufferedOutputStream bw = null;
            try {
                for(int i=0; i< files.length; i++) {
                    fileName = files[i].getOriginalFilename();
                    File outFile = new File(configTools.getUploadPath()+fileName);
                    FileUtils.copyInputStreamToFile(files[i].getInputStream(), outFile);
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
        return "上传成功！";
    }
}
