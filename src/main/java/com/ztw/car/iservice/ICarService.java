package com.ztw.car.iservice;

import com.ztw.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zsl-pc on 2016/9/15.
 */
public interface ICarService extends JpaRepository<Car, Integer> {

//    @Query("FROM Car c")
//    public Page<Car> pageAll(Pageable pageable);
//
//    @Query("FROM Car c WHERE c.status=?1 ")
//    public Page<Car> pageAll(String status, Pageable pageable);
//
//    public Car findById();

//    public Car findByCarTypeAndCarNo(String carType, String carNo);

//    public Car findByEngineNo(String engineNo);

//    public Car findByFrameNo(String frameNo);
}
