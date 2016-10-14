package com.ztw.car.model;

import javax.persistence.*;

/**
 * Created by zsl-pc on 2016/9/15.
 */
@Entity
@Table(name = "t_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 对应的车辆信息对象Id */
    @Column(name = "info_id")
    private Integer infoId;

    /** 品牌名称 */
    @Column(name = "brand_name")
    private String brandName;

    /** 号牌号码 */
    @Column(name = "car_no")
    private String carNo;

    /** 车辆系列名称 */
    @Column(name = "car_serial")
    private String carSerial;

    /** 号牌种类 */
    @Column(name = "car_type")
    private String carType;

    /** 发动机号 */
    @Column(name = "engine_no")
    private String engineNo;

    /** 车架号 */
    @Column(name = "frame_no")
    private String frameNo;

    /** 状态，1：在库；2：已租；3：维修；4：已售；10：报废 */
    private String status;

    /** 销售标记，1：可售；0：不可售 */
    @Column(name = "sale_flag")
    private Integer saleFlag;

    /** 如果可出售，设计售价 */
    private Float price;

    /** 来源标识，1：新购； 2：二手购 */
    @Column(name = "from_flag")
    private Integer fromFlag;

    public Integer getFromFlag() {
        return fromFlag;
    }

    public void setFromFlag(Integer fromFlag) {
        this.fromFlag = fromFlag;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(Integer saleFlag) {
        this.saleFlag = saleFlag;
    }

    public String getCarSerial() {
        return carSerial;
    }

    public void setCarSerial(String carSerial) {
        this.carSerial = carSerial;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
