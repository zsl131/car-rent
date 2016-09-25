package com.ztw.timers;

import com.ztw.car.iservice.IOrdersService;
import com.ztw.car.model.Orders;
import com.ztw.car.tools.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 系统定时器
 * Created by zsl-pc on 2016/9/21.
 */
@Component
public class SystemTasks {

    @Autowired
    private IOrdersService ordersService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    /**
     * 处理订单是否逾期的定时器
     * 每天凌晨12：30分执行
     */
//    @Scheduled(cron = "0 30 0 * * ?")
    @Scheduled(cron = "0 46 9 * * ?")
    public void processOrdersOverdue() {
        List<Orders> ordersList = ordersService.findByCon();
        for(Orders orders : ordersList) {
            boolean isOverdue = DateTools.isOverdue(orders.getCreateDate(), orders.getDays()); //是否逾期
            if(isOverdue) {
                ordersService.updateOverdue(1, orders.getId());
                //TODO 需要以短信或其他方式通知管理员和驾驶员订单已过期。
            }
        }
    }
}
