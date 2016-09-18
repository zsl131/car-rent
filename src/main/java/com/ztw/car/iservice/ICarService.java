package com.ztw.car.iservice;

import com.ztw.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zsl-pc on 2016/9/15.
 */
public interface ICarService extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

    public Car findById(Integer id);

    public Car findByCarTypeAndCarNo(String carType, String carNo);

    public Car findByEngineNo(String engineNo);

    public Car findByFrameNo(String frameNo);
}
