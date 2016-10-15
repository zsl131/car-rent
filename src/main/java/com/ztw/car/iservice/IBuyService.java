package com.ztw.car.iservice;

import com.ztw.car.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zsl-pc on 2016/9/18.
 */
public interface IBuyService extends JpaRepository<Buy, Integer>, JpaSpecificationExecutor<Buy> {

    @Query("SELECT COUNT(id) FROM Buy")
    public Long queryCount();
}
