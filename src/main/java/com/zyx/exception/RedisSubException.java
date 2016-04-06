package com.zyx.exception;

/**
 * Created by zhangyuanxin on 2016/4/1.
 */
public class RedisSubException extends Exception {
    public RedisSubException(String message) {
        super(message);
    }

    public RedisSubException(String message, Throwable throwable){
        super(message, throwable);
    }
}
