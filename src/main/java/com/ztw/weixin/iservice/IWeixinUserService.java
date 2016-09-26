package com.ztw.weixin.iservice;

import com.ztw.weixin.model.WeixinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2016/9/18.
 */
public interface IWeixinUserService extends JpaRepository<WeixinUser, Integer>,JpaSpecificationExecutor<WeixinUser>{

    public WeixinUser findByOpenId(String openId);

    @Modifying
    @Transactional
    @Query("UPDATE WeixinUser wx SET wx.bindingStatus=?2 WHERE wx.openId=?1")
    public void updateStatus(String openId, Integer status);
}
