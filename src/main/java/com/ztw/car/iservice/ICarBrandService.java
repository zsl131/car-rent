package com.ztw.car.iservice;

import com.ztw.car.model.CarBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zsl-pc on 2016/9/14.
 */
public interface ICarBrandService extends JpaRepository<CarBrand, Integer>, JpaSpecificationExecutor<CarBrand> {

    @Query("FROM CarBrand cb ")
    public Page<CarBrand> pageAll(Pageable pageable);

    @Query("FROM CarBrand ")
    public Page<CarBrand> pageAll(Specification<CarBrand> spec, Pageable pageable);

    public CarBrand findById(Integer id);

    @Query("SELECT MAX(orderNo) FROM CarBrand ")
    public Integer findMaxOrderNo();

    @Query("SELECT COUNT(id) FROM CarBrand")
    public Long queryCount() ;
}
