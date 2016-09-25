package com.ztw.legal.service;

import com.ztw.legal.model.Legal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 马旭 on 2016/9/19.
 */
public interface ILegalService extends JpaRepository<Legal, Integer>, JpaSpecificationExecutor<Legal> {

    public Legal findById(Integer id);

    @Query("From Legal leg Where leg.tenantSfz=?1")
    Page<Legal> pageByTenantSfz(String tenantSfz, Pageable pageable);

    @Query("From Legal leg Where leg.cphm=?1")
    Page<Legal> pageByCphm(String cphm, Pageable pageable);

    @Query("From Legal leg Where leg.rentId = ?1")
    public List<Legal> getLegalByRentId(Integer rentId);

    @Query("From Legal leg Where leg.cphm = :cphm and leg.cpzl = :cpzl and leg.legalong = :legalong")
    public Legal findOne(@Param("cphm") String cphm, @Param("cpzl") String cpzl, @Param("legalong") Long legalong);

    @Transactional
    @Modifying
    @Query("UPDATE Legal l SET l.status='1' WHERE l.cpzl=?2 AND l.cphm=?3 AND l.legalong IN (?1)")
    public void updateStatus(Long [] longs, String carType, String carNo);

    @Transactional
    @Modifying
    @Query("UPDATE Legal l SET l.status='1' WHERE l.cpzl=?2 AND l.cphm=?3 AND l.legalong NOT IN (?1)")
    public void updateStatusByNotIn(Long [] longs, String carType, String carNo);

    public List<Legal> findByCphmAndCpzl(String cphm, String cpzl);

    @Query("SELECT COUNT(id) FROM Legal l ")
    public Long queryCount();

    @Query("SELECT COUNT(id) FROM Legal l WHERE l.status=?1")
    public Long queryCount(String status);
}
