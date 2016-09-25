package com.ztw.deposit.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 保证金实体类
 * Created by 马旭 on 2016/9/7.
 */
@Entity
@Table(name = "t_deposit")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 租户的姓名
     */
    private String tenantName;

    /**
     * 租户身份证号
     */
    private String tenantSfz;

    /**
     * 租户联系电话
     */
    private String phone;

    /**
     * 租赁ID:租车订单ID（订单实体类里应有被租车辆的相关信息）
     */
    private Integer rentId;

    /**
     * 保证金数额，单位：元
     */
    private Double money;

    /**
     * 违章罚款
     */
    private Double legalMoney;

    /**
     * 其他扣除款项
     */
    private Double forfeitMoney;

    /**
     * 其他扣除款项备注
     */
    private String forfeitComments;

    /**
     * 记录实际退还的保证金
     */
    private Double returnMoney;

    /**
     * 保证金交纳开始日，也就是取车的日期
     */
    private Date startTime;

    /**
     * 租车归还日，保证金退还时间是一个月以后，归还时需要扣除违章罚款
     */
    private Date endTime;

    /**
     * 保证金实际退还日期
     */
    private Date returnTime;

    /**
     * 保证金状态
     * 1： 未退还租客
     * 2： 已退还
     */
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantSfz() {
        return tenantSfz;
    }

    public void setTenantSfz(String tenantSfz) {
        this.tenantSfz = tenantSfz;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getForfeitMoney() {
        return forfeitMoney;
    }

    public void setForfeitMoney(Double forfeitMoney) {
        this.forfeitMoney = forfeitMoney;
    }

    public Double getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(Double returnMoney) {
        this.returnMoney = returnMoney;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getLegalMoney() {
        return legalMoney;
    }

    public void setLegalMoney(Double legalMoney) {
        this.legalMoney = legalMoney;
    }

    public String getForfeitComments() {
        return forfeitComments;
    }

    public void setForfeitComments(String forfeitComments) {
        this.forfeitComments = forfeitComments;
    }
}
