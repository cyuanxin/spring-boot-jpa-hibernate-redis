package com.jinfuzi.opstd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zhangyuanxin on 2016/3/26.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private String host;
    private Integer port;
    private Integer maxActive;

    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        return new StringRedisTemplate(jedisConnectionFactory());
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }
}
