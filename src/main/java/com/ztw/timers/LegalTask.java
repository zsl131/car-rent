package com.ztw.timers;

import com.ztw.car.iservice.ICarService;
import com.ztw.car.model.Car;
import com.ztw.legal.tools.QueryLegalTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 车辆违章信息定时器
 * @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
 * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
 * @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
 * @Scheduled(cron="* /5 * * * * *") ：通过cron表达式定义规则
 * Created by zsl-pc on 2016/9/23.
 */
@Component
public class LegalTask {

    @Autowired
    private ICarService carService;

    @Autowired
    private QueryLegalTools queryLegalTools;

    //每天晚上1点执行违章查询
    @Scheduled(cron = "0 0 1 * * ?")
    public void queryAllLegal() {
        List<Car> carList = carService.findAll();
        for(Car c : carList) {
            queryLegalTools.queryLegalAndSave(c);
        }
    }
}
