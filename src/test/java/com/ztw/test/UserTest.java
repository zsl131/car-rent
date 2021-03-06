package com.ztw.test;

import com.ztw.basic.auth.iservice.IUserService;
import com.ztw.basic.auth.model.User;
import com.ztw.basic.auth.service.MenuServiceImpl;
import com.ztw.basic.tools.PageableTools;
import com.ztw.car.iservice.ICarBrandService;
import com.ztw.car.iservice.ICarService;
import com.ztw.car.model.Car;
import com.ztw.car.model.CarBrand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("zsl")
public class UserTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private MenuServiceImpl menuServiceImpl;

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private ICarService carService;

    @Test
    public void testQueryCount() {
        Long count = carService.queryCount();
        System.out.println("车辆数量==="+count);
    }

    @Test
    public void testUpdateCar() {
        carService.updateStatus(2, "1");
    }

    @Test
    public void testListCars() {
        List<Car> carList = carService.listCars("0000");
        for(Car c : carList) {
            System.out.println(c.getCarNo());
        }
    }

    @Test
    public void testCarBrand() {
        Page<CarBrand> datas = carBrandService.findAll(new Specification<CarBrand>(){
            @Override
            public Predicate toPredicate(Root<CarBrand> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.<Integer>get("id"), 1));
                return predicate;
            }
        }, PageableTools.basicPage(0));

        System.out.println("========datas.length : "+datas.getTotalElements());
    }

    @Test
    public void testCarBrand2() {
        Page<CarBrand> datas = carBrandService.pageAll(new Specification<CarBrand>(){
            @Override
            public Predicate toPredicate(Root<CarBrand> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.<Integer>get("id"), 1));
                return predicate;
            }
        }, PageableTools.basicPage(0));

        System.out.println("========datas.length : "+datas.getTotalElements());
    }

    @Test
    public void testAddUser() {
        for(int i=150; i<450; i++) {
            User u = new User();
            u.setCreateDate(new Date());
            u.setNickname("测试用户"+i);
            u.setStatus(1);
            u.setUsername("testuanme"+i);
            userService.save(u);
        }
    }

    @Test
    public void menuTest() {
        Integer [] ids = new Integer[]{1, 4};
        menuServiceImpl.updateSort(ids);
    }
}
