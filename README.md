# 简介

## jpa-hibernate

* 在本地只需要装上mysql，配置jdbc:mysql://localhost/opstd
* hbm2ddl.auto=create 会自动配置secheme
* 在test文件下就可以运行testItem方法

## redis

* 用了springRedisTemplate

* RedisCacheProcessor 封装了redis cache 操作方法 

* RedisSubProcessor 是 redis pub sub 方法
  1. application:redis.host 你的redishost地址
  2. application:activateTopic，tradetopic 写topic name, 默认test1,test2(这是两个topic，一次监听可以多个。在RedisSubProcessor注册)
  3. RedisMessageListener 监听处理消息，令起个redis客户端，往test1，test2发布msg，就可以看到控制台打印消息

