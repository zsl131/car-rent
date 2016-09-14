package com.ztw.deposit.service;

import com.ztw.deposit.model.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by 马旭 on 2016/9/13.
 */
public interface IDepositService extends JpaRepository<Deposit, Integer> {

    Deposit findById(Integer id);

    @Query("From Deposit dep Where dep.tenantSfz=?1")
    Page<Deposit> pageByTenantSfz(String tenantSfz, Pageable pageable);

    @Query("From Deposit dep")
    Page<Deposit> pageAll(Pageable pageable);

}
