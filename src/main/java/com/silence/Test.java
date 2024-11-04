package com.silence;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author silence
 * @since 2024/11/4 14:16
 **/
public class Test {

    private static JedisPool jedisPool;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(16);
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(2);
        poolConfig.setMinEvictableIdleTimeMillis(60000);
        poolConfig.setTimeBetweenEvictionRunsMillis(30000);

        jedisPool = new JedisPool(poolConfig, "8.138.84.94", 6400, 2000, "IB68aIJwf");
    }

    public static void main(String[] args) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("key", "name");
            String value = jedis.get("test");
            System.out.println("Value: " + value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
