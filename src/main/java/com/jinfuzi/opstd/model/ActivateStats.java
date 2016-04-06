package com.jinfuzi.opstd.model;

import com.jinfuzi.opstd.model.base.BaseModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhangyuanxin on 2016/3/31.
 */
@Entity(name = "opstd_activate_stats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(true)
public class ActivateStats extends BaseModel{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "today_count")
    private Long todayCount;
    @Column(name = "till_now_count")
    private Long tillNowCount;
    @Column(name = "record_date")
    @Type(type = "date")
    private Date recordDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(Long todayCount) {
        this.todayCount = todayCount;
    }

    public Long getTillNowCount() {
        return tillNowCount;
    }

    public void setTillNowCount(Long tillNowCount) {
        this.tillNowCount = tillNowCount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}
