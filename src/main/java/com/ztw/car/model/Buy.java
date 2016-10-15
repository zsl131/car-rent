package com.ztw.car.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 购买订单
 * Created by 钟述林 393156105@qq.com on 2016/10/15 23:51.
 */
@Entity
@Table(name = "t_buy")
public class Buy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 客户Id */
    @Column(name = "costumer_id")
    private Integer costumerId;

    /** 客户姓名 */
    @Column(name = "costumer_name")
    private String costumerName;

    /** 客户联系电话 */
    @Column(name = "costumer_phone")
    private String costumerPhone;

    /** 客户性别，1：男；2：女 */
    @Column(name = "costumer_sex")
    private String costumerSex;

    /** 客户身份证号 */
    @Column(name = "costumer_identity")
    private String costumerIdentity;

    /** 客户联系地址 */
    @Column(name = "costumer_address")
    private String costumerAddress;

    /** 客户年龄 */
    @Column(name = "costumer_age")
    private Integer costumerAge;

    /** 车辆种类Id */
    @Column(name = "type_id")
    private Integer typeId;

    /** 车辆种类名称 */
    @Column(name = "type_name")
    private String typeName;

    /** 品牌Id */
    @Column(name = "brand_id")
    private Integer brandId;

    /** 品牌名称 */
    @Column(name = "brand_name")
    private String brandName;

    /** 所属车辆信息Id */
    @Column(name = "info_id")
    private Integer infoId;

    /** 车辆Id */
    @Column(name = "car_id")
    private Integer carId;

    /** 车辆系列名称 */
    @Column(name = "car_serial")
    private String carSerial;

    /** 车牌号码 */
    @Column(name = "car_no")
    private String carNo;

    /** 号牌种类，基本都是02：小型汽车 */
    @Column(name = "car_type")
    private String carType;

    /** 创建日期 */
    @Column(name = "create_date")
    private Date createDate;

    /** 微信号 */
    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(Integer costumerId) {
        this.costumerId = costumerId;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        this.costumerName = costumerName;
    }

    public String getCostumerPhone() {
        return costumerPhone;
    }

    public void setCostumerPhone(String costumerPhone) {
        this.costumerPhone = costumerPhone;
    }

    public String getCostumerSex() {
        return costumerSex;
    }

    public void setCostumerSex(String costumerSex) {
        this.costumerSex = costumerSex;
    }

    public String getCostumerIdentity() {
        return costumerIdentity;
    }

    public void setCostumerIdentity(String costumerIdentity) {
        this.costumerIdentity = costumerIdentity;
    }

    public String getCostumerAddress() {
        return costumerAddress;
    }

    public void setCostumerAddress(String costumerAddress) {
        this.costumerAddress = costumerAddress;
    }

    public Integer getCostumerAge() {
        return costumerAge;
    }

    public void setCostumerAge(Integer costumerAge) {
        this.costumerAge = costumerAge;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarSerial() {
        return carSerial;
    }

    public void setCarSerial(String carSerial) {
        this.carSerial = carSerial;
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
}
