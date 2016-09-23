package com.ztw.car.iservice;

import com.ztw.car.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zsl-pc on 2016/9/18.
 */
public interface IOrdersService extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {

    public Orders findById(Integer id);

    @Query("UPDATE Orders o SET o.status=?2 , o.msg=?3 WHERE o.id=?1")
    @Modifying
    @Transactional
    public void updateStatus(Integer id, String status, String msg);

    @Query("SELECT COUNT(id) FROM Orders")
    public Long queryCount();

    @Query("SELECT COUNT(id) FROM Orders o WHERE o.status=?1")
    public Long queryCount(String status);

    @Query("SELECT COUNT(id) FROM Orders o WHERE o.isOverdue=?1")
    public Long queryCountByOverdue(Integer overdue);
}
