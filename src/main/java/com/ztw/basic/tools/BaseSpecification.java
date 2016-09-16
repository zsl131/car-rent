package com.ztw.basic.tools;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by zsl-pc on 2016/9/15.
 */
public class BaseSpecification<T> implements Specification<T> {



    private SearchCriteria criteria;

    public BaseSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        String opt = criteria.getOperation();
        String key = criteria.getKey();
        String value = criteria.getValue().toString();
        if (opt.equalsIgnoreCase("ge")) { //大于等于
            return builder.greaterThanOrEqualTo(
                    root.<String> get(key), value);
        } else if(opt.equalsIgnoreCase("gt")) { //大于
            return builder.greaterThan(root.get(key), value);
        } else if(opt.equalsIgnoreCase("le")) { //小于等于
            return builder.lessThanOrEqualTo(root.get(key), value);
        } else if(opt.equalsIgnoreCase("lt")) { //小于
            return builder.lessThan(root.get(key), value);
        } else if(opt.equalsIgnoreCase("likeb")) { // like '%?'
            return builder.like(root.get(key), "%"+value);
        } else if(opt.equalsIgnoreCase("likee")) { // like '?%'
            return builder.like(root.get(key), value+"%");
        } else if(opt.equalsIgnoreCase("like") || opt.equalsIgnoreCase("likebe")) { //like '%?%'
            return builder.like(root.get(key), "%"+value+"%");
        } else if(opt.equalsIgnoreCase("nlikeb")) { // not like '%?'
            return builder.notLike(root.get(key), "%"+value);
        } else if(opt.equalsIgnoreCase("nlikee")) { // not like '?%'
            return builder.notLike(root.get(key), value + "%");
        } else if(opt.equalsIgnoreCase("nlike") || opt.equalsIgnoreCase("nlikebe")) { //not like '%?%'
            return builder.notLike(root.get(key), "%"+value+"%");
        } else if(opt.equalsIgnoreCase("eq")) { //equal
            return builder.equal(root.get(key), value);
        } else if(opt.equalsIgnoreCase("neq")) { //not equal
            return builder.notEqual(root.get(key), value);
        } else if(opt.equalsIgnoreCase("isnull")) { // is null
            return builder.isNull(root.get(key));
        }
        return null;
    }
}
