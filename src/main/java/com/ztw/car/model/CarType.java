package com.ztw.car.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2016/9/8.
 */
@Entity
@Table(name = "t_car_type")
public class CarType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String  name;

    @Column(name = "order_no")
    private Integer orderNo;

    private String logo;

    public String getLogo() {
        return logo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
