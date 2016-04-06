package com.jinfuzi.opstd.redis;

import com.jinfuzi.opstd.redis.listener.RedisMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyuanxin on 2016/3/30.
 */
@Component
public class RedisSubProcessor {
    @Value("${redis.sub.activateTopic}")
    private String activateTopic;
    @Value("${redis.sub.tradeTopic}")
    private String tradeTopic;

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageListener());
    }


    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);
        container.addMessageListener(messageListener(), new ChannelTopic(tradeTopic));
        container.addMessageListener(messageListener(), new ChannelTopic(activateTopic));
        return container;
    }



}
