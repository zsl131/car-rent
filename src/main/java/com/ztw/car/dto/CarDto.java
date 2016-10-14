package com.ztw.car.dto;

import com.ztw.car.model.Car;
import com.ztw.car.model.CarInfo;

/**
 * Created by zsl-pc on 2016/10/14.
 */
public class CarDto {

    private Car car;

    private CarInfo info;

    public Car getCar() {
        return car;
    }

    public CarInfo getInfo() {
        return info;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setInfo(CarInfo info) {
        this.info = info;
    }

    public CarDto() {}

    public CarDto(Car car, CarInfo info) {
        this.car = car; this.info = info;
    }
}
