package com.ztw.car.model;

import javax.persistence.*;

/**
 * Created by zxt on 2016/9/7.
 */
@Entity
@Table(name = "t_car_info")
public class CarInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    /** 品牌Id */
    @Column(name="brand_id")
    private Integer brandId;

    /** 品牌名称 */
    @Column(name="brand_name")
    private String brandName;

    /** 种类Id */
    @Column(name="type_id")
    private Integer typeId;

    /** 种类名称 */
    @Column(name="type_name")
    private String typeName;

    /** 品牌系列，如：大众朗逸 */
    private String ppxl;

    /** 厢数，如：三厢、两厢、SUV */
    private String clxs;

    /** 发动机动力，如：1.6、2.0T */
    private String fdjdl;

    /** 操作类型，自动、手动、手自一体 */
    private String czlx;

    /** 出厂年代 */
    private Integer ccnd;

    /** 配置类型，如：豪华版、精英版 */
    private String pzlx;

    /** 座位数量 */
    private Integer zwsl;

    /** 车门数量 */
    private Integer cmsl;

    /** 燃料类型，如：柴油、汽油 */
    private String rllx;

    /** 变速箱类型，如：AT */
    private String bsxlx;

    /** 排量 */
    private Integer pl;

    /** 燃油标号，如：93号汽油、97号汽油 */
    private String rybh;

    /** 驱动方式，如：前驱、后驱、四驱 */
    private String qdfs;

    /** 进气方式，如：自然吸气 */
    private String jqfs;

    /** 天窗类型，如：无、单天窗、全景天窗 */
    private String tclx;

    /** 油箱容量 */
    private Integer yxrl;

    /** 音箱数量 */
    private Integer yxsl;

    /** 座椅类型，如：皮革座椅 */
    private String zylx;

    /** 倒车雷达，如：有、无 */
    private String dcld;

    /** 倒车影像，如：有、无 */
    private String dcyx;

    /** 气囊数量 */
    private Integer qnsl;

    /** 影音类型，如：DVD、CD、无 */
    private String yylx;

    /** GPS导航，如：有、无 */
    private String gps;

    /** 可选颜色，可直接输入多个 */
    private String kxys;

    /** 图片地址1 */
    private String tpdz1;

    /** 图片地址2 */
    private String tpdz2;

    /** 图片地址3 */
    private String tpdz3;

    /** 图片地址4 */
    private String tpdz4;

    /** 备注 */
    @Lob
    private String remark;

    /** 日均价 */
    private Float rjj;

    /** 车辆号牌种类，如：02：小型汽车等 */
    private String clzl = "02";

    public String getClzl() {
        return clzl;
    }

    public void setClzl(String clzl) {
        this.clzl = clzl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPpxl() {
        return ppxl;
    }

    public void setPpxl(String ppxl) {
        this.ppxl = ppxl;
    }

    public String getClxs() {
        return clxs;
    }

    public void setClxs(String clxs) {
        this.clxs = clxs;
    }

    public String getFdjdl() {
        return fdjdl;
    }

    public void setFdjdl(String fdjdl) {
        this.fdjdl = fdjdl;
    }

    public String getCzlx() {
        return czlx;
    }

    public void setCzlx(String czlx) {
        this.czlx = czlx;
    }

    public Integer getCcnd() {
        return ccnd;
    }

    public void setCcnd(Integer ccnd) {
        this.ccnd = ccnd;
    }

    public String getPzlx() {
        return pzlx;
    }

    public void setPzlx(String pzlx) {
        this.pzlx = pzlx;
    }

    public Integer getZwsl() {
        return zwsl;
    }

    public void setZwsl(Integer zwsl) {
        this.zwsl = zwsl;
    }

    public Integer getCmsl() {
        return cmsl;
    }

    public void setCmsl(Integer cmsl) {
        this.cmsl = cmsl;
    }

    public String getRllx() {
        return rllx;
    }

    public void setRllx(String rllx) {
        this.rllx = rllx;
    }

    public String getBsxlx() {
        return bsxlx;
    }

    public void setBsxlx(String bsxlx) {
        this.bsxlx = bsxlx;
    }

    public Integer getPl() {
        return pl;
    }

    public void setPl(Integer pl) {
        this.pl = pl;
    }

    public String getRybh() {
        return rybh;
    }

    public void setRybh(String rybh) {
        this.rybh = rybh;
    }

    public String getQdfs() {
        return qdfs;
    }

    public void setQdfs(String qdfs) {
        this.qdfs = qdfs;
    }

    public String getJqfs() {
        return jqfs;
    }

    public void setJqfs(String jqfs) {
        this.jqfs = jqfs;
    }

    public String getTclx() {
        return tclx;
    }

    public void setTclx(String tclx) {
        this.tclx = tclx;
    }

    public Integer getYxrl() {
        return yxrl;
    }

    public void setYxrl(Integer yxrl) {
        this.yxrl = yxrl;
    }

    public Integer getYxsl() {
        return yxsl;
    }

    public void setYxsl(Integer yxsl) {
        this.yxsl = yxsl;
    }

    public String getZylx() {
        return zylx;
    }

    public void setZylx(String zylx) {
        this.zylx = zylx;
    }

    public String getDcld() {
        return dcld;
    }

    public void setDcld(String dcld) {
        this.dcld = dcld;
    }

    public String getDcyx() {
        return dcyx;
    }

    public void setDcyx(String dcyx) {
        this.dcyx = dcyx;
    }

    public Integer getQnsl() {
        return qnsl;
    }

    public void setQnsl(Integer qnsl) {
        this.qnsl = qnsl;
    }

    public String getYylx() {
        return yylx;
    }

    public void setYylx(String yylx) {
        this.yylx = yylx;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getKxys() {
        return kxys;
    }

    public void setKxys(String kxys) {
        this.kxys = kxys;
    }

    public String getTpdz1() {
        return tpdz1;
    }

    public void setTpdz1(String tpdz1) {
        this.tpdz1 = tpdz1;
    }

    public String getTpdz2() {
        return tpdz2;
    }

    public void setTpdz2(String tpdz2) {
        this.tpdz2 = tpdz2;
    }

    public String getTpdz3() {
        return tpdz3;
    }

    public void setTpdz3(String tpdz3) {
        this.tpdz3 = tpdz3;
    }

    public String getTpdz4() {
        return tpdz4;
    }

    public void setTpdz4(String tpdz4) {
        this.tpdz4 = tpdz4;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Float getRjj() {
        return rjj;
    }

    public void setRjj(Float rjj) {
        this.rjj = rjj;
    }
}
