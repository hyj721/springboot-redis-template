package com.uestc;

import com.uestc.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Resource
    private IRedisService redisService;

    @org.junit.Test
    public void test() {
        String cacheKey = "test-queue";
        RBlockingQueue<Long> blockingQueue = redisService.getBlockingQueue(cacheKey);
        RDelayedQueue<Long> delayedQueue = redisService.getDelayedQueue(blockingQueue);
        delayedQueue.offer(1L, 3, TimeUnit.SECONDS);
        delayedQueue.offer(2L, 3, TimeUnit.SECONDS);
    }

    @org.junit.Test
    public void test_clear() {
        String cacheKey = "test-queue";
        RBlockingQueue<Long> blockingQueue = redisService.getBlockingQueue(cacheKey);
        RDelayedQueue<Long> delayedQueue = redisService.getDelayedQueue(blockingQueue);
        blockingQueue.clear();
    }

    @org.junit.Test
    public void test_remove() {
        String cacheKey = "test-queue";
        redisService.remove(cacheKey);
    }

}
