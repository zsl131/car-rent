package com.ztw.car.iservice;

import com.ztw.car.model.CarInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zxt on 2016/9/7.
 */
public interface ICarInfoService extends JpaRepository<CarInfo,Integer>, JpaSpecificationExecutor<CarInfo> {

    public CarInfo findById(Integer id);
}
