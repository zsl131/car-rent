package com.ztw.car.iservice;

import com.ztw.basic.tools.PageableTools;
import com.ztw.car.model.CarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by admin on 2016/9/8.
 */
public interface ICarTypeService extends JpaRepository<CarType,Integer> {
    @Query("select c.id,c.typeName from CarType c")
    List<CarType> findIDAndTypeName();

    @Query("from CarType ct order by ct.createDate asc")
    Page<CarType> pageAll(Pageable pageable);

}