package com.ztw.people.iservice;

import com.ztw.people.model.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zsl-pc on 2016/9/13.
 */
public interface IPeopleService extends JpaRepository<People, Integer>, JpaSpecificationExecutor<People> {

    public People findById(Integer id);

    //通过身份证号获取客户信息，身份证号与对象一一对应
    public People findByIdentity(String identity);

    @Query("FROM People p ")
    public Page<People> pageAll(Pageable pageable);

    @Query("FROM People p WHERE p.status=?1 ")
    public Page<People> pageByStatus(String status, Pageable pageable);

    @Query("SELECT COUNT(id) FROM People")
    public Long queryCount();
}
