package com.ztw.weixin.iservice;

import com.ztw.weixin.model.WeixinMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zsl-pc on 2016/9/26.
 */
public interface IWeixinMessageService extends JpaRepository<WeixinMessage, Integer>, JpaSpecificationExecutor<WeixinMessage> {
}
