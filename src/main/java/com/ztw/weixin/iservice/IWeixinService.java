package com.ztw.weixin.iservice;

import com.ztw.weixin.model.WeiXin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by admin on 2016/9/18.
 */
public interface IWeixinService extends JpaRepository<WeiXin, Integer>,JpaSpecificationExecutor<WeiXin>{
}
