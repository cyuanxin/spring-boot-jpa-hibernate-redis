package com.zyx;

import com.zyx.model.Item;
import com.zyx.repository.ItemRepository;
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
 * Created by zhangyuanxin on 2016/3/26 .
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
        item.setDate(new Date());
        item.setDescription("zyx");
        itemRepository.saveAndFlush(item);
        for (Item item1 : itemRepository.findAll()) {
            System.out.println(item1);
        }
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


}
