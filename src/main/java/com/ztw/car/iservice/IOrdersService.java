package com.ztw.car.iservice;

import com.ztw.car.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/18.
 */
public interface IOrdersService extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {

    public Orders findById(Integer id);

    /**
     * 修改订单状态
     * @param id 主键Id
     * @param status 状态
     * @param msg 原因
     * @param backDate 归还日期，Date类型
     * @param backday 归还时间，String类型，yyyy-MM-dd 格式
     * @param dackDateLong 归还时间，Long类型
     */
    @Query("UPDATE Orders o SET o.status=?2 , o.msg=?3, o.backDate=?4, o.backDay=?5, o.backDateLong=?6 WHERE o.id=?1")
    @Modifying
    @Transactional
    public void updateStatus(Integer id, String status, String msg, Date backDate, String backday, Long dackDateLong);

    @Query("SELECT COUNT(id) FROM Orders")
    public Long queryCount();

    @Query("SELECT COUNT(id) FROM Orders o WHERE o.status=?1")
    public Long queryCount(String status);

    @Query("SELECT COUNT(id) FROM Orders o WHERE o.isOverdue=?1")
    public Long queryCountByOverdue(Integer overdue);

    @Query("FROM Orders o WHERE (o.createDateLong >= ?1 AND o.backDateLong IS NOT NULL AND o.backDateLong <= ?1) OR (o.createDateLong >= ?1 AND o.status='1')")
    public Orders queryOne(Long locationLong);

    @Modifying
    @Transactional
    @Query("UPDATE Orders o SET o.lllegalCount=o.lllegalCount+1, o.lllegalMoney=o.lllegalMoney+?1, o.lllegalScore=o.lllegalScore+?2 WHERE o.id=?3")
    public void updateLegal(Float money, Integer score, Integer objId);

    @Query("FROM Orders o WHERE o.status='1' AND o.isOverdue=0 ")
    public List<Orders> findByCon();

    @Modifying
    @Transactional
    @Query("UPDATE Orders o SET o.isOverdue=?1 WHERE o.id=?2")
    public void updateOverdue(Integer overdue, Integer id);
}
