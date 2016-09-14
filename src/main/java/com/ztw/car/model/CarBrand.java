package com.ztw.car.model;

import javax.persistence.*;

/**
 * 车辆品牌
 * Created by zsl-pc on 2016/9/14.
 */
@Entity
@Table(name = "t_car_brand")
public class CarBrand {

    /** 主键Id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "order_no")
    private Integer orderNo;

    /** 品牌名称 */
    private String name;

    /** 品牌图标 */
    private String logo;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public String getLogo() {
        return logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}
