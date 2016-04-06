package com.jinfuzi.opstd.Service.RedisSubChannel;

import com.jinfuzi.opstd.exception.RedisSubException;

/**
 * Created by zhangyuanxin on 2016/3/31.
 */
public interface IHandleChannelMsg<T, E> {
    void handleMsg(String msg);

    E validateAndConvertMsg(T t) throws RedisSubException;
}
