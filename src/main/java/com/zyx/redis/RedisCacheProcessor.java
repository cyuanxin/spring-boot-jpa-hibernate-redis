package com.zyx.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhangyuanxin on 2016/3/30.
 */
@Component
public class RedisCacheProcessor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 左进
     * 对于redis，是一组双向队列
     *
     * @param key
     * @param value
     * @return
     */
    public Long push(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 左出
     *
     * @param key
     * @return
     */
    public String pop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 右进
     *
     * @param key
     * @param value
     * @return
     */
    public Long in(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 右出
     *
     * @param key
     * @return
     */
    public String out(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 栈/队列长
     *
     * @param key
     * @return
     */
    public Long length(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    /**
     * 范围检索
     * 当end=-1，表示全部
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> range(String key, int start, int end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除
     *
     * @param key
     * @param i
     * @param value
     */
    public void remove(String key, long i, String value) {
        stringRedisTemplate.opsForList().remove(key, i, value);
    }

    /**
     * 队列
     *
     * @param key
     * @param index
     * @return
     */
    public String index(String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    /**
     * 置值
     *
     * @param key
     * @param index
     * @param value
     */
    public void setList(String key, long index, String value) {
        stringRedisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 裁剪
     *
     * @param key
     * @param start
     * @param end
     */
    public void trim(String key, long start, int end) {
        stringRedisTemplate.opsForList().trim(key, start, end);
    }

    public void setKey(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
