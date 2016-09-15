package com.ztw.car.iservice;

import com.ztw.basic.tools.PageableTools;
import com.ztw.car.model.CarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by admin on 2016/9/8.
 */
public interface ICarTypeService extends JpaRepository<CarType,Integer> {

    @Query("FROM CarType ct ")
    public Page<CarType> pageAll(Pageable pageable);

    public CarType findById(Integer id);

    @Query("SELECT MAX(orderNo) FROM CarType ")
    public Integer findMaxOrderNo();

}