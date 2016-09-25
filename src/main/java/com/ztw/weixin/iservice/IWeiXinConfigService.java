package com.ztw.weixin.iservice;

import com.ztw.weixin.model.WeiXinConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by admin on 2016/9/19.
 */

public interface IWeiXinConfigService extends JpaRepository<WeiXinConfig,Integer>{
}
