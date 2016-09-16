package com.ztw.car.iservice;

import com.ztw.car.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by admin on 2016/9/8.
 */
public interface ICarTypeService extends JpaRepository<CarType,Integer>, JpaSpecificationExecutor<CarType> {

    public CarType findById(Integer id);

    @Query("SELECT MAX(orderNo) FROM CarType ")
    public Integer findMaxOrderNo();
}