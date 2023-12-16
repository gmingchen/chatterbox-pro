package com.slipper.core.redis.utils;

import com.slipper.common.utils.Utils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 * @author gumingchen
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 设置过期key
     * @param key 键值
     * @param value 值
     * @param expire 过期时间（秒）
     */
    public boolean set(String key, Object value, long expire) {
        try {
            redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置不过期key
     * @param key 键值
     * @param value 值
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置过期key
     * @param key 键值
     * @param value 值
     * @param expire 过期时间（秒）
     */
    public Boolean setIfAbsent(String key, Object value, long expire) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 通过key获取value
     * @param key 键值
     * @return
     */
    public Optional<Object> get(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(value);
    }

    /**
     * 通过key获取value
     * @param key 键值
     * @param clazz class对象
     * @param <T> 类型
     * @return
     */
    public <T> Optional<T> get(String key, Class<T> clazz) {
        Optional<Object> optional = get(key);
        return optional.map(clazz::cast);
    }

    /**
     * 获取数组
     * @param key 键值
     * @param clazz class对象
     * @param <T> 类型
     * @return
     */
    public <T> List<T> getList(String key, Class<T> clazz) {
        return get(key).map(obj -> Utils.castList(obj, clazz)).orElseGet(ArrayList::new);
    }

    /**
     * 删除
     * @param key 键值-一个或者多个
     */
    public void delete(String ...key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    /**
     * 删除
     * @param keys 键值数组
     */
    public void delete(Collection<Object> keys) {
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
    /**
     * 模糊匹配删除
     * @param key
     */
    public void fuzzyDelete(String key) {
        key += key.endsWith(":") ? "*" : ":*";
        Set<Object> keys = redisTemplate.keys(key);
        delete(keys);
    }

    /**
     * 设置过期时间
     * @param key 键值
     * @param expire 过期时间（秒）
     * @return
     */
    public boolean expire(String key, long expire) {
        if (expire > 0) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    /**
     * 获取过期时间
     * @param key 键值
     * @return 时间（秒）：0-永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键值
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}

