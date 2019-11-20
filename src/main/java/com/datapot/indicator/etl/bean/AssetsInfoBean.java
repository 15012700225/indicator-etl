package com.datapot.detection.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ad_logon_off")
public class AssetsInfoBean implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Transient
    private String displayName; //用户名

    @Transient
    private String company; //公司

    @Transient
    private String department; //部门

    @Column(name = "account_name")
    private String account_name; //AD账号

    @Column(name = "event_ID")
    private Integer event_id; //登录状态id

    @Transient
    private String logStatus; //登录状态

    @Column(name = "website_address")
    private String associateIp; //登录目标ip

    @Column(name = "record_time")
    private String record_time; //登录时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(String logStatus) {
        this.logStatus = logStatus;
    }

    public String getAssociateIp() {
        return associateIp;
    }

    public void setAssociateIp(String associateIp) {
        this.associateIp = associateIp;
    }

    public String getRecord_time() {
        return record_time;
    }

    public void setRecord_time(String record_time) {
        this.record_time = record_time;
    }
}
