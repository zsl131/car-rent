package com.ztw.basic.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

/**
 * Created by zsl-pc on 2016/9/11.
 */
@Configuration
@Component
public class ConfigTools { // extends WebMvcConfigurerAdapter

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

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置静态资源路径
        registry.addResourceHandler("*//**")
                .addResourceLocations("classpath:/", "classpath:/static/", "classpath:/public/", "file:"+File.separator+uploadPath);
//        super.addResourceHandlers(registry);
    }*/
}
