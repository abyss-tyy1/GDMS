package com.GDMS.dto;

import com.alibaba.fastjson.JSON;
import com.GDMS.entity.ClassInfoEntity;
import com.GDMS.utils.exception.BusinessException;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class AsynchronousHandler implements Runnable{
    private long threadId;
    private StringRedisTemplate stringRedisTemplate;

    public AsynchronousHandler(long threadId,StringRedisTemplate stringRedisTemplate) {
        this.threadId = threadId;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void run() {
        ClassInfoEntity classInfoEntity = new ClassInfoEntity();
        //处理中
        classInfoEntity.setId(0);
        stringRedisTemplate.opsForValue().set(threadId+"",JSON.toJSONString(classInfoEntity),60, TimeUnit.SECONDS);
        try {
            for (int i = 0; i < 100000; i++) {
                System.out.println("测试线程池" + i);
            }
            //处理完毕
            classInfoEntity.setId(1);
            stringRedisTemplate.opsForValue().set(threadId+"",JSON.toJSONString(classInfoEntity),60, TimeUnit.SECONDS);
        }catch (Exception e) {
            classInfoEntity.setId(3);
            stringRedisTemplate.opsForValue().set(threadId+"",JSON.toJSONString(classInfoEntity),60, TimeUnit.SECONDS);
            throw new BusinessException("异步执行错误"+e.toString());
        }

    }


}
