package com.jinfuzi.opstd.repository;

import com.jinfuzi.opstd.model.ActivateStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangyuanxin on 2016/3/31.
 */
public interface ActivateStatsRepository extends JpaRepository<ActivateStats, Long> {
    ActivateStats findByRecordDate(@Param("record_date") Date date);

    List<ActivateStats> findAllByOrderByRecordDateDesc();

}
