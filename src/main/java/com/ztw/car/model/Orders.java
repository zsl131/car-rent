package com.ztw.car.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单
 * Created by zsl-pc on 2016/9/18.
 */
@Entity
@Table(name = "t_orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 订单类型，0：电话订单（即由管理员下单）；1：客户订单 */
    private String type;

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

    /** 租赁天数 */
    private Integer days;

    /** 归还日期 */
    @Column(name = "back_day")
    private String backDay;

    /** 应该归还日期 */
    @Column(name = "need_back_day")
    private String needBackDay;

    /** 应支付金额 */
    @Column(name = "need_money")
    private Float needMoney;

    /** 实际支付金额 */
    private Float money;

    /** 下单日期 */
    @Column(name = "create_date")
    private Date createDate;

    /** 订单状态，0：未提车；1：已提车；2：已归还；3：已取消； */
    private String status;

    /** 是否逾期，0：正常；1：已逾期 */
    @Column(name = "is_overdue")
    private Integer isOverdue;

    /** 客户备注 */
    @Column(name = "costumer_remind")
    private String costumerRemind;

    /** 操作员备注 */
    private String remind;

    /** 违章条数 */
    @Column(name = "lllegal_count")
    private Integer lllegalCount;

    /** 违章罚金 */
    @Column(name = "lllegal_money")
    private Float lllegalMoney;

    /** 违章扣分数 */
    @Column(name = "lllegal_score")
    private Integer lllegalScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getBackDay() {
        return backDay;
    }

    public void setBackDay(String backDay) {
        this.backDay = backDay;
    }

    public String getNeedBackDay() {
        return needBackDay;
    }

    public void setNeedBackDay(String needBackDay) {
        this.needBackDay = needBackDay;
    }

    public Float getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(Float needMoney) {
        this.needMoney = needMoney;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Integer isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getCostumerRemind() {
        return costumerRemind;
    }

    public void setCostumerRemind(String costumerRemind) {
        this.costumerRemind = costumerRemind;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public Integer getLllegalCount() {
        return lllegalCount;
    }

    public void setLllegalCount(Integer lllegalCount) {
        this.lllegalCount = lllegalCount;
    }

    public Float getLllegalMoney() {
        return lllegalMoney;
    }

    public void setLllegalMoney(Float lllegalMoney) {
        this.lllegalMoney = lllegalMoney;
    }

    public Integer getLllegalScore() {
        return lllegalScore;
    }

    public void setLllegalScore(Integer lllegalScore) {
        this.lllegalScore = lllegalScore;
    }
}