package com.test.land.landparent.admin.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.redis.connection.MessageListener;


public interface RedisService {

    /**
     * 将字符串永久性的存入redis
     * @param key
     * @param value
     */
    void setValue(final String key, final String value);

    /**
     * 将对象永久性的存入redis
     * @param key
     * @param value
     */
    <T extends Serializable> void setValue(final String key, final T value);

    /**
     * 将对象存入redis，设置一个过期时间seconds
     * @param key
     * @param value
     * @param seconds
     */
    <T extends Serializable> void setValue(final String key, final T value, final long seconds);

    /**
     * 将字符串存入redis，设置一个过期时间seconds
     * @param key
     * @param value
     * @param seconds
     */
    void setStrValue(final String key, final String value, final long seconds);

    /**
     * 给一个key设置过期时间,这个过期时间是以秒来计算，比如10秒后key过期
     * @param key
     * @param seconds
     * @return
     */
    boolean setKeyExpireTime(final String key, final long seconds);

    /**
     * 通过key得到序列化后的对象
     * @param key
     * @return
     */
    <T extends Serializable> T getObjectByKey(final String key);

    /**
     * 取字符串
     * @param key
     * @return
     */
    String getStrValue(final String key);

    /**
     * 将一个key删除掉
     * @param key
     */
    void remove(final String key);

    /**
     * 将一个key删除掉 注意：必须是序列化的key
     * @param key
     */
    <T extends Serializable> void removeT(final T key);

    /**
     * 判断key是否存在 存储的类型是字符串时适用
     * @param key
     * @return boolean
     */
    boolean exists(final String key);

    /**
     * 判断key是否存在存储的类型是对象时适用
     * @param key
     * @return boolean
     */
    <T extends Serializable> boolean existsT(final T key);

    /**
     * 根据key模糊查询
     * @param key
     * @return
     */
    <T extends Serializable> List<T> getLikeKey(final String key);

    /**
     * 发布消息，消息是字符串
     * @param channel
     * @param message
     */
    void publish(String channel, String message);

    /**
     * 发布消息，消息是个对象
     * @param channel
     * @param message
     */
    <T extends Serializable> void publish(String channel, T message);

    /**
     * 订阅消息
     * @param listener
     * @param channel
     */
    void subsribe(MessageListener listener, String channel);

    /**
     * 原子计数器+1
     * @param key
     */
    Long incr(String key);

    /**
     * 原子计数器-1
     * @param key
     */
    Long decr(String key);

    /**
     * 原子计数器+value
     * @param key
     * @param value
     * @return
     */
    Long incrBy(String key, long value);

    /**
     * 原子计数器-value
     * @param key
     * @param value
     */
    Long decrBy(String key, long value);

}
