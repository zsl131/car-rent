package com.ztw.weixin.iservice;

import com.ztw.weixin.model.WeiXinMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by admin on 2016/9/20.
 */
public interface IWeiXinMenuService extends JpaRepository<WeiXinMenu,Integer>, JpaSpecificationExecutor<WeiXinMenu> {

    @Query("from WeiXinMenu wm where wm.pid is null")
    public List<WeiXinMenu> findAllPidIsNull();

    @Query("from WeiXinMenu wm where wm.pid=?1")
    public List<WeiXinMenu> findSonMenuByPid(Integer pid);

    @Query("SELECT MAX(m.orderNo) FROM WeiXinMenu m WHERE m.pid IS NULL")
    public Integer queryMaxOrderNo();

    @Query("SELECT MAX(m.orderNo) FROM WeiXinMenu m WHERE m.pid=?1")
    public Integer queryMaxOrderNo(Integer pid);
}
