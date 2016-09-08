package com.ztw.car.iservice;

import com.ztw.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zxt on 2016/9/7.
 */
public interface ICarService extends JpaRepository<Car,Integer> {

}
