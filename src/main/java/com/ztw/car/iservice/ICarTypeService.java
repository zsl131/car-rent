package com.ztw.car.iservice;

import com.ztw.car.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by admin on 2016/9/8.
 */
public interface ICarTypeService extends JpaRepository<CarType,Integer>{

}
