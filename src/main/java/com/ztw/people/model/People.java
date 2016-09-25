package com.ztw.people.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 客户管理
 * Created by zsl-pc on 2016/9/13.
 */
@Entity
@Table(name = "t_people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 姓名 */
    private String name;

    /** 性别 1：男；2：女 */
    private String sex;

    /** 身份证号 */
    private String identity;

    /** 家庭住址 */
    private String address;

    /** 联系电话 */
    private String phone;

    /** 年龄 */
    private Integer age;

    /** 状态，1：正常；-1：黑名单 */
    private String status;

    /** 采集日期 */
    @Column(name = "create_date")
    private Date createDate;

    /** 身份证照片路径 */
    @Column(name = "iden_pic")
    private String idenPic;

    /** 身份证反面照片路径 */
    @Column(name = "iden_back_pic")
    private String idenBackPic;

    /** 驾驶证照片路径 */
    @Column(name = "drive_pic")
    private String drivePic;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIdenBackPic() {
        return idenBackPic;
    }

    public void setIdenBackPic(String idenBackPic) {
        this.idenBackPic = idenBackPic;
    }

    public String getDrivePic() {
        return drivePic;
    }

    public String getIdenPic() {
        return idenPic;
    }

    public void setDrivePic(String drivePic) {
        this.drivePic = drivePic;
    }

    public void setIdenPic(String idenPic) {
        this.idenPic = idenPic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
