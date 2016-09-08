package com.ztw.car.model;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by zxt on 2016/9/7.
 */
@Entity
@Table(name = "t_car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    //车名
    @Column(name = "car_name")
    private String carName;
    //级别：小型车，中型车，suv...
    @Column(name = "car_type")
    private Integer carType;
    //排量
    @Column(name = "out_put")
    private Float output;
    //驱动方式： 1:前驱，2:后驱，3:四驱
    private Integer drive;
    //变数箱,1:手动，2:自动
    @Column(name = "variable_box")
    private Integer variableBox;
    //生产方式，进口，国产 (0：全部，1：国产，2：进口)
    @Column(name = "production_mode")
    private Integer productionMode;
    //颜色
    private String color;

    //年代款 2013款,2014款....
    @Column(name = "year_style")
    private Integer yearStyle;
    //创建时间
    @Column(name = "create_date")
    private Date createDate;

    //图片存储路径
    private String img;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setDrive(Integer drive) {
        this.drive = drive;
    }

    public Integer getDrive() {
        return drive;
    }

    public Float getOutput() {
        return output;
    }

    public void setOutput(Float output) {
        this.output = output;
    }

    public Integer getProductionMode() {
        return productionMode;
    }

    public void setProductionMode(Integer productionMode) {
        this.productionMode = productionMode;
    }

    public Integer getVariableBox() {
        return variableBox;
    }

    public void setVariableBox(Integer variableBox) {
        this.variableBox = variableBox;
    }

    public Integer getYearStyle() {
        return yearStyle;
    }

    public void setYearStyle(Integer yearStyle) {
        this.yearStyle = yearStyle;
    }
}
