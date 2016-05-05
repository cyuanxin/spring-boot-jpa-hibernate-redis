package com.zyx.redis.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by zhangyuanxin on 2016/3/30.
 */
public class RedisMessageListener implements MessageListener {
    private Logger log = LoggerFactory.getLogger(RedisMessageListener.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = (String)redisTemplate.getValueSerializer().deserialize(message.getBody());
        String channel = (String)redisTemplate.getStringSerializer().deserialize(pattern);
        log.info("new msg:" + msg + " channel:" + channel);
    }


}
