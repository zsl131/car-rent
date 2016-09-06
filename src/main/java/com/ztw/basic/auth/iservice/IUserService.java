package com.ztw.basic.auth.iservice;

import com.ztw.basic.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zsl-pc on 2016/8/31.
 */
public interface IUserService extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
}
