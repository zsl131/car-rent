package com.ztw.deposit.model;

import javax.persistence.*;

/**
 * 保证金实体类
 * Created by 马旭 on 2016/9/7.
 */
@Entity
@Table(name = "a_deposit")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 租户的姓名
     */
    private String tenant_name;

    /**
     * 租户身份证号
     */
    private String tenant_sfz;

    /**
     * 租户联系电话
     */
    private String phone;

    /**
     * 租赁ID:租车订单ID（订单实体类里应有被租车辆的相关信息）
     */
    private Integer rent_id;

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

    public String getTenant_name() {
        return tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        this.tenant_name = tenant_name;
    }

    public String getTenant_sfz() {
        return tenant_sfz;
    }

    public void setTenant_sfz(String tenant_sfz) {
        this.tenant_sfz = tenant_sfz;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRent_id() {
        return rent_id;
    }

    public void setRent_id(Integer rent_id) {
        this.rent_id = rent_id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
