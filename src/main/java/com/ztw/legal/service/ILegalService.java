package com.ztw.legal.service;

import com.ztw.legal.model.Legal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 马旭 on 2016/9/19.
 */
public interface ILegalService extends JpaRepository<Legal, Integer>{

    public Legal findById(Integer id);

    @Query("From Legal leg Where leg.tenantSfz=?1")
    Page<Legal> pageByTenantSfz(String tenantSfz, Pageable pageable);

    @Query("From Legal leg Where leg.cphm=?1")
    Page<Legal> pageByCphm(String cphm, Pageable pageable);

    @Query("From Legal leg Where leg.rentId = ?1")
    public List<Legal> getLegalByRentId(String rentId);

    @Query("From Legal leg Where leg.cphm = :cphm and leg.cpzl = :cpzl and leg.legalong = :legalong")
    public Legal findOne(@Param("cphm") String cphm, @Param("cpzl") String cpzl, @Param("legalong") Long legalong);

}
