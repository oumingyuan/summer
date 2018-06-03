package com.mingyuan.summer.test;

import redis.clients.jedis.Jedis;

public class RedisJava {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("120.79.223.29");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());

        jedis.set("orderId", "1");

        System.out.println(jedis.get("orderId"));
    }

}
