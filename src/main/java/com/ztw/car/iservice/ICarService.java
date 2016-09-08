package com.ztw.car.iservice;

import com.ztw.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zxt on 2016/9/7.
 */
public interface ICarService extends JpaRepository<Car,Integer> {
    @Modifying
    @Transactional
    @Query("update Car c set c.carName= ?2 where c.id= ?1")
    public void updateCarName(Integer id,String carName);
}
