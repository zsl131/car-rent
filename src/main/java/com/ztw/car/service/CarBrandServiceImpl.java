package com.ztw.car.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by zsl-pc on 2016/9/14.
 */
@Component
@Transactional
public class CarBrandServiceImpl {

    @PersistenceContext
    EntityManager em;

    public void updateSort(Integer [] ids) {
        int index = 1;
//        String hql = "UPDATE CarBrand cb set cb.orderNo=? WHERE cb.id=?";
        for(Integer id : ids) {
            em.createQuery("update CarBrand cb set cb.orderNo="+(index++)+" WHERE cb.id="+id).executeUpdate();
        }
    }
}
