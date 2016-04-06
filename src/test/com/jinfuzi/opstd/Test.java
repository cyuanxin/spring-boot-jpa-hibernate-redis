package jinfuzi.opstd;

import com.jinfuzi.opstd.Application;
import com.jinfuzi.opstd.Service.ActivationService;
import com.jinfuzi.opstd.model.ActivateRecord;
import com.jinfuzi.opstd.model.ActivateStats;
import com.jinfuzi.opstd.model.Item;
import com.jinfuzi.opstd.repository.ActivateRecordRepository;
import com.jinfuzi.opstd.repository.ActivateStatsRepository;
import com.jinfuzi.opstd.repository.ItemRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangyuanxin on 2016/3/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Application.class)  // 2
@WebAppConfiguration   // 3
public class Test {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @org.junit.Test
    public void testItem() {
        List<Item> items = itemRepository.findAll();
        Item item = new Item();
        item.setChecked(true);
        item.setDescription("dddd");
        itemRepository.saveAndFlush(item);
        items.forEach(item1 -> System.out.println("test:" + item1.toString()));
    }

    @org.junit.Test
    public void testRedis() {
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        String key = "spring.boot.redis.test";
        if (!this.redisTemplate.hasKey(key)) {
            ops.set(key, "zhangyuanxin");
        }

        System.out.println("Found key " + key + ", value=" + ops.get(key));
        System.out.println(ops.get(key));
//        List<String> meanings = redisTemplate.opsForList().range("je", 0, -1);
    }

    @Autowired
    private ActivateStatsRepository activateStatsRepository;
    @org.junit.Test
    public void testActivateStats() {
        ActivateStats activateStats = new ActivateStats();
        activateStats.setRecordDate(new Date());
        activateStatsRepository.saveAndFlush(activateStats);
        List<ActivateStats> activateStatses = activateStatsRepository.findAll();
        activateStatses.stream().forEach(activateStats1 -> System.out.println(activateStats1));
    }

    @Autowired
    private ActivateRecordRepository activateRecordRepository;
    public void testActivateRecordRepository() {

        for (int i = 0; i < 10; i++) {
//            (activateRecord);
        }
    }
    @Autowired
    private ActivationService activationService;
    @org.junit.Test
    public void testActivationService() {

        for (int i = 0; i < 10; i++) {
            ActivateRecord activateRecord = new ActivateRecord();
            activateRecord.setAddress("深圳");
            activateRecord.setRequestNetwork("4g");
            activateRecord.setIp("192.111.11.11");
            activateRecord.setUid(424324324L);
            activateRecord.setActivateDate(new Date());
            activateRecord.setDevice("iphone");
            activateRecord.setSource("滚雪球");
            activateRecord.setCreateDate(new Date());
            activationService.saveRecordAndUpdateStas(activateRecord);
        }
    }

    @org.junit.Test
    public void testGetCount() {
        System.out.println("total" + activationService.getTotalCount() + "  today" + activationService.getTodayCount());
        System.out.println("cache:total" + activationService.getTotalCount() + "  today" + activationService.getTodayCount());
        System.out.println("cache2:total" + activationService.getTotalCount() + "  today" + activationService.getTodayCount());
    }

//    private ActivateStatsRepository activateStatsRepository;
    @org.junit.Test
    public void testStasReposity() {
        System.out.println(activateRecordRepository.findAll());
        System.out.println(activateRecordRepository.findAll());
    }
}
