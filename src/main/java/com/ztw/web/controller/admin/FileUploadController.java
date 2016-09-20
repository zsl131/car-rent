package com.ztw.web.controller.admin;

import com.ztw.basic.json.JsonTools;
import com.ztw.basic.tools.ConfigTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by zsl-pc on 2016/9/19.
 */
@Controller
public class FileUploadController {

    @Autowired
    private ConfigTools configTools;

    /** 通过Base64上传图片 */
    @RequestMapping(value = "web/uploadByBase64", method = RequestMethod.POST)
    public @ResponseBody String uploadByBase64(HttpServletRequest request, String imgStr, String path, String fileName) {
        if(imgStr==null || "".equalsIgnoreCase(imgStr)) {return JsonTools.buildJsonNormalStr(0, "无数据");}
        path = path==null||"".equalsIgnoreCase(path)?"":path;
        //如果文件存在先删除
        if(fileName!=null && !"".equalsIgnoreCase(fileName)) {
            File file = new File(configTools.getUploadPath("") + fileName);
            if(file.exists()) { file.delete(); }
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            File outFile = new File(configTools.getUploadPath(path) + "/" + UUID.randomUUID().toString()+".jpg");
            OutputStream out = new FileOutputStream(outFile);
            out.write(b);
            out.flush();
            out.close();
            String outPath = outFile.getAbsolutePath().replace(configTools.getUploadPath(), "");
            return JsonTools.buildJsonNormalStr(1, outPath);
        } catch (IOException e) {
            return JsonTools.buildJsonNormalStr(0, e.getMessage());
        }
    }
}
