package com.ztw.test;

import com.ztw.RootApplication;
import com.ztw.basic.auth.iservice.IUserService;
import com.ztw.basic.auth.model.User;
import com.ztw.basic.auth.service.MenuServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Created by zsl-pc on 2016/9/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(RootApplication.class)
public class UserTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private MenuServiceImpl menuServiceImpl;

    @Test
    public void testAddUser() {
        for(int i=150; i<450; i++) {
            User u = new User();
            u.setCreateDate(new Date());
            u.setNickname("测试用户"+i);
            u.setStatus(1);
            u.setUsername("testuanme"+i);
            userService.save(u);
        }
    }

    @Test
    public void menuTest() {
        Integer [] ids = new Integer[]{1, 4};
        menuServiceImpl.updateSort(ids);
    }
}
