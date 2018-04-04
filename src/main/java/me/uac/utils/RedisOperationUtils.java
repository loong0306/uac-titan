package me.uac.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author dragon
 * @date 2018年03月30日22:35:29
 */
public class RedisOperationUtils {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value){
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.set(key,value);
    }

    public void set(String key,String value, Long time, TimeUnit timeUnit){
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.set(key,value,time, timeUnit);
    }

    public void increment(String key, Long value){
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.increment(key,value);
    }

    public boolean hasKey(String key){
        return stringRedisTemplate.hasKey(key);
    }

    public Long getValue(String key){
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        return Long.parseLong(valueOps.get(key));
    }

    public String getKey(String key) {
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        if(stringRedisTemplate.hasKey(key)){
            return ops.get(key);
        }
        return null;
    }

    public void deleteKey(String key){
        stringRedisTemplate.delete(key);
    }
}
