package com.ztw.car.iservice;

import com.ztw.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zsl-pc on 2016/9/15.
 */
public interface ICarService extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

    public Car findById(Integer id);

    public Car findByCarTypeAndCarNo(String carType, String carNo);

    public Car findByEngineNo(String engineNo);

    public Car findByFrameNo(String frameNo);

    @Query("FROM Car c WHERE c.status='1' AND (c.brandName LIKE CONCAT('%',?1,'%') OR c.carNo LIKE CONCAT('%',?1,'%') OR c.carSerial LIKE CONCAT('%',?1,'%'))")
    public List<Car> listCars(String param);

    @Query("UPDATE Car c SET c.status=?2 WHERE c.id=?1")
    @Modifying
    @Transactional
    public void updateStatus(Integer id, String status);

    @Query("UPDATE Car c SET c.status=?2 WHERE c.id=?1")
    @Modifying
    @Transactional
    public void updateStatusByOrders(Integer id, String status);

    @Query("SELECT COUNT(id) FROM Car ")
    public Long queryCount();

    @Query("SELECT COUNT(id) FROM Car c WHERE c.status=?1")
    public Long queryCount(String status);
}
