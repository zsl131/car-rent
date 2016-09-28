package com.ztw.web.controller.app;

import com.ztw.car.model.CarInfo;

/**
 * Created by zsl-pc on 2016/9/28.
 */
public class CarInfoDto {

    /** 车辆信息对象 */
    private CarInfo carInfo;

    /** 在库数量 */
    private Long inCount;

    public CarInfoDto(CarInfo carInfo, Long inCount) {
        this.carInfo = carInfo; this.inCount = inCount;
    }

    public CarInfoDto() {}

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    public void setInCount(Long inCount) {
        this.inCount = inCount;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public Long getInCount() {
        return inCount;
    }
}
