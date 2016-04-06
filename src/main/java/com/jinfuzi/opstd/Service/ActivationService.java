package com.jinfuzi.opstd.Service;

import com.jinfuzi.opstd.model.ActivateRecord;
import com.jinfuzi.opstd.model.ActivateStats;
import com.jinfuzi.opstd.repository.ActivateRecordRepository;
import com.jinfuzi.opstd.repository.ActivateStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangyuanxin on 2016/4/1.
 */
@Service
public class ActivationService {
    @Autowired
    private ActivateStatsRepository activateStatsRepository;
    @Autowired
    private ActivateRecordRepository activateRecordRepository;
    @Cacheable
    public Long getTodayCount() {
        ActivateStats activateStats = activateStatsRepository.findByRecordDate(new Date());
        if (activateStats == null) {
            return 0L;
        } else {
            return activateStats.getTodayCount();
        }
    }

    public Long getTotalCount() {
        ActivateStats activateStats = activateStatsRepository.findByRecordDate(new Date());
        if (activateStats == null) {
            return 0L;
        } else {
            return activateStats.getTodayCount();
        }
    }

    /**
     * 保存record并且更新activateStas
     * @param activateRecord
     */
    @Transactional
    public void saveRecordAndUpdateStas(ActivateRecord activateRecord) {
        //保存record
        activateRecordRepository.save(activateRecord);
        //根据record的记录日期寻找对应的stas
        ActivateStats activateStat = activateStatsRepository.findByRecordDate(activateRecord.getActivateDate());
        //如果没有stas，说明是新的一天，
        if (activateStat == null) {
            //创建新的stat
            activateStat = new ActivateStats();
            //stat的今天激活数为1
            activateStat.setTodayCount(1L);
            activateStat.setRecordDate(activateRecord.getActivateDate());
            //查找目前最近的一天的stas，获取总激活数量
            List<ActivateStats> oldStas = activateStatsRepository.findAllByOrderByRecordDateDesc();
            //如果找不到，则总激活数量为1,否则最近的总数量+1
            if (oldStas.isEmpty()) {
                activateStat.setTillNowCount(1L);
            } else {
                activateStat.setTillNowCount(oldStas.get(0).getTillNowCount() + 1);
            }
            activateStatsRepository.saveAndFlush(activateStat);
            //如果有stas，说明还是今天，则今天的激活数和总激活数各+1
        } else {
            activateStat.setTillNowCount(activateStat.getTillNowCount() + 1);
            activateStat.setTodayCount(activateStat.getTodayCount() + 1);
        }
    }


    /**
     * num 返回num行数据 pageNum=0 只有第一页
     *
     * @param num
     * @return
     */
    public List<ActivateRecord> getRecentActivation(Integer num) {
        return activateRecordRepository.findAllByOrderByIdDesc(new PageRequest(0, num));
    }
}
