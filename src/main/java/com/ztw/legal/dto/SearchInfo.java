package com.ztw.legal.dto;

/**
 * legal search information
 * @author 马旭
 */
public class SearchInfo {

    /**
     * 号牌种类
     */
    private String hpzl;
    
    /**
     * 号牌号码
     */
    private String hphm;
    
    /**
     * 开始时间
     */
    private String kssj;
    
    /**
     * 结束时间
     */
    private String jssj;
    
    /**
     * 违章省份
     */
    private String wzsf;

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getWzsf() {
        return wzsf;
    }

    public void setWzsf(String wzsf) {
        this.wzsf = wzsf;
    }

    /**
     * 违章查询必要信息构造方法
     * @param hpzl 号牌种类
     * @param hphm 号牌号码
     * @param kssj 查询开始时间
     * @param jssj 查询结束时间
     * @param wzsf 违章省份
     */
    public SearchInfo(String hpzl, String hphm, String kssj, String jssj, String wzsf) {
        setHpzl(hpzl);
        setHphm(hphm);
        setKssj(kssj);
        setJssj(jssj);
        setWzsf(wzsf);
    }

    /**
     * 空构造方法
     */
    public SearchInfo() {
        // 空构造方法
    }
}
