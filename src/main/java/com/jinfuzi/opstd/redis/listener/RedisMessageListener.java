package com.jinfuzi.opstd.redis.listener;


import com.jinfuzi.opstd.Service.RedisSubChannel.IHandleChannelMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * Created by zhangyuanxin on 2016/3/30.
 */
public class RedisMessageListener implements MessageListener {
    private Logger log = LoggerFactory.getLogger(RedisMessageListener.class);
    @Value("${redis.sub.activateTopic}")
    private String activateTopic;
    @Value("${redis.sub.tradeTopic}")
    private String tradeTopic;
    private IHandleChannelMsg handleActivationMsg;


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = "";
        try {
            channel = new String(pattern, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("wrong occurred while decode channel " + e);
        }
        log.info("new activation msg:" + message);
        if (channel.equals(activateTopic)) {
            handleActivationMsg.handleMsg(message.getBody().toString());
        } else if (channel.equals(tradeTopic)) {

        }
    }


}
