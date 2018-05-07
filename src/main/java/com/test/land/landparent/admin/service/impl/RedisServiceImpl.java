package com.test.land.landparent.admin.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.test.land.landparent.admin.common.Encode;
import com.test.land.landparent.admin.service.RedisService;

/**
 * Redis缓存操作类（哨兵模式集群）
 * @author 10058393
 *
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    RedisTemplate<?, ?> redisTemplate;

    @Override
    public void setValue(final String key, final String value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key.getBytes(), value.getBytes());
                logger.debug("save key:" + key + ",value:" + value);
                return null;
            }
        });
    }

    @Override
    public <T extends Serializable> void setValue(final String key, final T value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = key.getBytes();
                byte[] byteValue = Encode.encode(value);
                connection.set(byteKey, byteValue);
                logger.debug("save key:" + key + ",value:" + value);
                return null;
            }
        });
    }

    @Override
    public <T extends Serializable> void setValue(final String key, final T value, final long seconds) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = key.getBytes();
                byte[] byteValue = Encode.encode(value);
                connection.set(byteKey, byteValue);
                connection.expire(byteKey, seconds);
                logger.debug("save key:" + key + ",value:" + value + ",expire:" + seconds);
                return null;
            }
        });
    }

    @Override
    public void setStrValue(final String key, final String value, final long seconds) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = key.getBytes();
                byte[] byteValue = value.getBytes();
                connection.set(byteKey, byteValue);
                connection.expire(byteKey, seconds);
                logger.debug("save key:" + key + ",value:" + value + ",expire:" + seconds);
                return null;
            }
        });
    }

    @Override
    public boolean setKeyExpireTime(final String key, final long seconds) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = key.getBytes();
                return connection.expire(byteKey, seconds);
            }
        });
    }

    @Override
    public String getStrValue(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = key.getBytes();
                if (connection.exists(byteKey)) {
                    byte[] byteValue = connection.get(byteKey);
                    String value = new String(byteValue);
                    logger.debug("get key:" + key + ",value:" + value);
                    return value;
                }
                logger.error("valus does not exist!key:" + key);
                return null;
            }
        });
    }

    @Override
    public <T extends Serializable> T getObjectByKey(final String key) {
        return redisTemplate.execute(new RedisCallback<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = key.getBytes();
                if (connection.exists(byteKey)) {
                    byte[] byteValue = connection.get(byteKey);
                    Object value = Encode.decode(byteValue);
                    logger.debug("get key:" + key + ",value:" + value);
                    return (T) value;
                }
                logger.error("valus does not exist!key:" + key);
                return null;
            }
        });
    }

    @Override
    public void remove(final String key) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) {
                connection.del(key.getBytes());
                return null;
            }
        });
    }

    @Override
    public <T extends Serializable> void removeT(final T key) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = Encode.encode(key);
                if (connection.exists(byteKey)) {
                    connection.del(byteKey);
                }
                return null;
            }
        });
    }

    @Override
    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = key.getBytes();
                return connection.exists(byteKey);
            }
        });
    }

    @Override
    public <T extends Serializable> boolean existsT(final T key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = Encode.encode(key);
                return connection.exists(byteKey);
            }
        });
    }

    @Override
    public <T extends Serializable> List<T> getLikeKey(final String key) {
        return redisTemplate.execute(new RedisCallback<List<T>>() {
            @SuppressWarnings("unchecked")
            @Override
            public List<T> doInRedis(RedisConnection connection) throws DataAccessException {
                Set<byte[]> set = connection.keys(key.getBytes());
                List<T> list = new ArrayList<T>();
                for (byte[] t : set) {
                    byte[] byteValue = connection.get(t);
                    Object value = Encode.decode(byteValue);
                    list.add((T) value);
                }
                return list;
            }
        });
    }

    @Override
    public void publish(final String channel, final String message) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.publish(channel.getBytes(), message.getBytes());
                return null;
            }
        });
    }

    @Override
    public <T extends Serializable> void publish(final String channel, final T message) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.publish(channel.getBytes(), Encode.encode(message));
                return null;
            }
        });
    }

    @Override
    public void subsribe(final MessageListener listener, final String channel) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.subscribe(listener, channel.getBytes());
                return null;
            }
        });
    }

    @Override
    public Long incr(String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.incr(key.getBytes());
            }
        });
    }

    @Override
    public Long decr(String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.decr(key.getBytes());
            }
        });
    }

    @Override
    public Long incrBy(String key, long value) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.incrBy(key.getBytes(), value);
            }
        });
    }

    @Override
    public Long decrBy(String key, long value) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.decrBy(key.getBytes(), value);
            }
        });
    }

}


