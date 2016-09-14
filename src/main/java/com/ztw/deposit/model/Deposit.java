package com.ztw.deposit.model;

import javax.persistence.*;

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
    private Integer money;

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

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

}
