package com.ztw.legal.dto;

/**
 * 租户基本信息
 * Created by 马旭 on 2016/9/21.
 */
public class TenantInfo {
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

    /**
     * 构造方法
     * @param tenantName 租户的姓名
     * @param tenantSfz 租户身份证号
     * @param phone 租户联系电话
     * @param rentId 租赁ID:租车订单ID（订单实体类里应有被租车辆的相关信息）
     */
    public TenantInfo(String tenantName, String tenantSfz, String phone, Integer rentId) {
        setTenantName(tenantName);
        setTenantSfz(tenantSfz);
        setPhone(phone);
        setRentId(rentId);
    }

    /**
     * 空构造方法
     */
    public TenantInfo() {
        // 空构造方法
    }
}
