package com.jinfuzi.opstd.model;

import com.jinfuzi.opstd.model.base.BaseModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by zhangyuanxin on 2016/3/31.
 */
@Entity(name = "opstd_activate_record")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(true)
public class ActivateRecord extends BaseModel {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    @NotNull
    private Long uid;
    @Column
    private String ip;
    @Column
    private String address;
    @Column(name = "activate_date")
    @NotNull
    private Date activateDate;
    @Column
    private String device;
    @Column(name = "request_network")
    private String requestNetwork;
    @Column(name = "source")
    private String source;
    @Column(name = "create_date", updatable = false, insertable = true)
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getRequestNetwork() {
        return requestNetwork;
    }

    public void setRequestNetwork(String requestNetwork) {
        this.requestNetwork = requestNetwork;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
