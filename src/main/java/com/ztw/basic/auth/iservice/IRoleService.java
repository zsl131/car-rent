package com.ztw.basic.auth.iservice;

import com.ztw.basic.auth.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zsl-pc on 2016/9/1.
 */
public interface IRoleService extends JpaRepository<Role, Integer> {

    public Role findBySn(String sn);

    public Role findById(Integer id);

    @Query("FROM Role r ")
    public Page<Role> pageAll(Pageable pageable);

    @Query("SELECT rm.mid FROM RoleMenu rm WHERE rm.rid=?1")
    public List<Integer> listRoleMenuIds(Integer roleId);
}
