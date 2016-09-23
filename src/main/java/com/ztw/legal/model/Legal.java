package com.ztw.legal.model;

import com.ztw.legal.dto.LegalDto;
import com.ztw.legal.dto.SearchInfo;
import com.ztw.legal.dto.TenantInfo;

import javax.persistence.*;

/**
 * 车辆违章实体类，用户记录租车违章的相关信息
 * Created by 马旭 on 2016/9/19.
 */
@Entity
@Table(name = "t_legal")
public class Legal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 租户的姓名
     */
    private String tenantName;

    /**
     * 租户身份证号
     */
    private String tenantSfz;

    /**
     * 租户联系电话
     */
    private String phone;

    /**
     * 租赁ID:租车订单ID（订单实体类里应有被租车辆的相关信息）
     */
    private Integer rentId;

    /**
     * 车牌号码
     */
    private String cphm;

    /**
     * 车牌种类
     */
    private String cpzl;

    /**
     * 处罚类型
     * 1：现场处罚
     * 2：非现场处罚
     */
    private String type;

    /**
     * 处罚机构
     */
    private String orgName;

    /**
     * 违法编号
     */
    private String legalNo;

    /**
     * 违法时间
     */
    private String time;

    /**
     * 违法行为
     */
    private String behavior;

    /**
     * 违法地点
     */
    private String address;

    /**
     * 处罚金额
     */
    private Double money;

    /**
     * 扣分
     */
    private Integer score;

    /**
     * 违法时间，Long类型
     */
    private Long legalong;

    /** 状态，0：未处理，1：已处理 */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantSfz() {
        return tenantSfz;
    }

    public void setTenantSfz(String tenantSfz) {
        this.tenantSfz = tenantSfz;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getLegalNo() {
        return legalNo;
    }

    public void setLegalNo(String legalNo) {
        this.legalNo = legalNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCpzl() {
        return cpzl;
    }

    public void setCpzl(String cpzl) {
        this.cpzl = cpzl;
    }

    public Long getLegalong() {
        return legalong;
    }

    public void setLegalong(Long legalong) {
        this.legalong = legalong;
    }

    /**
     * 空构造方法
     */
    public Legal() {
        // 空构造方法
    }

    /**
     * 构造租户基本信息
     * @param tenantInfo
     */
    public Legal(TenantInfo tenantInfo, SearchInfo searchInfo) {
        setTenantName(tenantInfo.getTenantName());
        setTenantSfz(tenantInfo.getTenantSfz());
        setPhone(tenantInfo.getPhone());
        setRentId(tenantInfo.getRentId());
        setCphm(searchInfo.getHphm());
        setCpzl(searchInfo.getHpzl());
    }

    /**
     *  设置违章信息
     * @param legalDto
     */
    public void setLegalDto(LegalDto legalDto) {
        setType(legalDto.getType());
        setOrgName(legalDto.getOrgName());
        setLegalNo(legalDto.getLegalNo());
        setTime(legalDto.getTime());
        setBehavior(legalDto.getBehavior());
        setAddress(legalDto.getAddress());
        setMoney(legalDto.getMoney());
        setScore(legalDto.getScore());
        setLegalong(legalDto.getLegalong());
    }

}
