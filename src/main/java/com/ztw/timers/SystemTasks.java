package com.ztw.timers;

import com.ztw.basic.auth.iservice.IUserService;
import com.ztw.basic.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统定时器
 * Created by zsl-pc on 2016/9/21.
 */
@Component
public class SystemTasks {

    @Autowired
    private IUserService userService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void testTime() {
//        System.out.println("现在时间："+sdf.format(new Date()));
    }

    @Scheduled(fixedRate = 8000)
    public void testUser() {
        /*List<User> list = userService.findAll();
        for(User u : list) {
            System.out.println(u.getNickname()+"===="+u.getUsername());
        }*/
    }
}
