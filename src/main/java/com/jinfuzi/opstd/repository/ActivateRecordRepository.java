package com.jinfuzi.opstd.repository;

import com.jinfuzi.opstd.model.ActivateRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangyuanxin on 2016/3/31.
 */
public interface ActivateRecordRepository extends JpaRepository<ActivateRecord, Long> {
    List<ActivateRecord> findAllByOrderByIdDesc(Pageable pageable);
}
