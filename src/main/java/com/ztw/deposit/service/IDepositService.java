package com.ztw.deposit.service;

import com.ztw.deposit.model.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 马旭 on 2016/9/13.
 */
public interface IDepositService extends JpaRepository<Deposit, Integer> {

    public Deposit findById(Integer id);

    @Query("From Deposit dep Where dep.rentId=?1")
    public Deposit findByRentId(Integer id);

    @Query("From Deposit dep Where dep.tenantSfz=?1")
    public Page<Deposit> pageByTenantSfz(String tenantSfz, Pageable pageable);

    @Query("From Deposit dep")
    public Page<Deposit> pageAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Deposit dep Set dep.forfeitMoney = ?2 Where dep.rentId = ?1")
    public int updateByRentId(Integer rentId, Double forfeitMoney);

}
