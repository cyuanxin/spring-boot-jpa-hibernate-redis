package com.zyx.redis.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * Created by zhangyuanxin on 2016/3/30.
 */
public class RedisMessageListener implements MessageListener {
    private Logger log = LoggerFactory.getLogger(RedisMessageListener.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = "";
        try {
            channel = new String(pattern, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("wrong occurred while decode channel " + e);
        }
        log.info("new activation msg:" + message);
    }


}
