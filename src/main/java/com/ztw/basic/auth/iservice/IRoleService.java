package com.ztw.basic.auth.iservice;

import com.ztw.basic.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zsl-pc on 2016/9/1.
 */
public interface IRoleService extends JpaRepository<Role, Integer> {

    public Role findBySn(String sn);

//    public List<Integer>
}
