package com.ztw.weixin.iservice;

import com.ztw.weixin.model.WeiXinMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by admin on 2016/9/20.
 */
public interface IWeiXinMenuService extends JpaRepository<WeiXinMenu,Integer> {

    @Query("from WeiXinMenu wm where wm.pid=?1")
    public Page<WeiXinMenu> findByPid(Integer pid,Pageable pageable);

    @Query("from WeiXinMenu wm where wm.pid is null")
    public Page<WeiXinMenu> findAllPidIsNull(Pageable pageable);

    @Query("from WeiXinMenu wm where wm.pid is null")
    public List<WeiXinMenu> findAllPidIsNull();


    @Query("from WeiXinMenu wm where wm.pid=?1")
    public List<WeiXinMenu> findSonMenuByPid(Integer pid);
}
