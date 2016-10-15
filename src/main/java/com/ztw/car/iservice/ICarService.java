package com.ztw.car.iservice;

import com.ztw.car.dto.CarDto;
import com.ztw.car.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT COUNT(c.id) FROM Car c WHERE c.status=?1")
    public Long queryCount(String status);

    @Query("SELECT COUNT(c.id) FROM Car c WHERE c.infoId=?1 AND c.status=?2")
    public Long queryCount(Integer infoId, String status);

    @Query("SELECT new com.ztw.car.dto.CarDto(c, ci) FROM Car c, CarInfo ci where c.saleFlag=1 AND (c.status!='4' AND c.status!='10') AND ci.id = c.infoId")
    Page<CarDto> querySale(Pageable pageable);

    @Query("SELECT new com.ztw.car.dto.CarDto(c, ci) FROM Car c, CarInfo ci WHERE ci.id=c.infoId AND c.id=?1")
    CarDto queryOne(Integer id);
}
