package com.ztw.car.iservice;

import com.ztw.car.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zsl-pc on 2016/9/18.
 */
public interface IOrdersService extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {

    public Orders findById(Integer id);
}
