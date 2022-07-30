package com.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheServiceImpl {
    @Autowired
    RedisTemplate<String, Object> template;

    @Test
    public void test002() {
        ValueOperations<String, Object> redisString = template.opsForValue();
        // SET key value: 设置指定 key 的值
        redisString.set("strKey1", "hello spring boot redis");
        // GET key: 获取指定 key 的值
        String value = (String) redisString.get("strKey1");
        System.out.println(value);

//        redisString.set("strKey2", new User(123, "theName"));
//        User user = (User) redisString.get("strKey2");
//        System.out.println(user);
    }


    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void contextLoads() {

        // 日志级别
        // 由低到高 trace < debug < info < warn < error
        // 可以调整需要输出的日志级别，日志就会只在这个级别以后的高级别生效
        logger.trace("这是 trace");
        logger.debug("调试 debug");
        // spring boot 默认使用的是 info 级别的
        // 没有指定级别的就使用 Spring Boot 默认规定的级别，root 级别
        logger.info("这是 info");
        logger.warn("这是 warn");
        logger.error("这是 error");
    }

}
