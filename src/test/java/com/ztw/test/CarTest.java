package com.ztw.test;

import com.ztw.basic.tools.PageableTools;
import com.ztw.car.dto.CarDto;
import com.ztw.car.iservice.ICarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zsl-pc on 2016/10/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("zsl")
public class CarTest {

    @Autowired
    private ICarService carService;

    @Test
    public void testList() {
        Page<CarDto> list = carService.querySale(PageableTools.basicPage(1));
        for(CarDto cd : list) {
            System.out.println(cd.getInfo().getBrandName()+cd.getInfo().getPpxl()+"===="+cd.getCar().getCarNo());
        }
    }

    @Test
    public void testLoad() {
        CarDto cd = carService.queryOne(17);
        System.out.println(cd==null);
        System.out.println(cd.getCar().getCarNo());
    }
}
