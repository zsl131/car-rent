package com.ztw.basic.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by zsl-pc on 2016/9/11.
 */
@Configuration
@Component
public class ConfigTools {

    @Value("${web.upload-path}")
    private String uploadPath;

    public String getUploadPath() {
        File f = new File(uploadPath);
        if(!f.exists()) {f.mkdirs();}
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
