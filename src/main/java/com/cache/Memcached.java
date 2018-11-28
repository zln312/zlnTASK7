package com.cache;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

import java.util.Date;
import java.util.Map;

public class Memcached {


    //构建缓存客户端
    private static MemCachedClient cachedClient;

    //单例模式实现客户端管理类
    private  static Memcached INSTANCE = new Memcached();

    private Memcached() {
        cachedClient = new MemCachedClient();

        //初始化SockIOPool,管理memcached的连接池
        SockIOPool pool = SockIOPool.getInstance();

        //设置缓存服务器列表，当使用分布式缓存的时候，可以指定多个缓存服务器。（这里应该设置为多个不同的服务器）


        String[] services = {"127.0.0.1:11211"};

        pool.setServers(services);
        pool.setFailover(true);
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 60 * 60 * 3); // 设置每个连接最大空闲时间3个小时
        pool.setMaintSleep(30);

        // 设置TCP的参数，连接超时等
        pool.setNagle( false );
        pool.setSocketTO( 3000 );
        pool.setSocketConnectTO( 0 );

        pool.setAliveCheck(true);
        pool.initialize();     //初始化连接池
    }

    /**
     * 获取缓存管理器唯一实例
     *
     * @return
     */

    public static Memcached getInstance() {
        return INSTANCE;
    }

    public Boolean add(String key, Object value) {
        return cachedClient.set(key, value);
    }

    public Boolean add(String key, Object value, Date expiry) {
        return   cachedClient.set(key, value, expiry);
    }

    public Boolean remove(String key) {
        return   cachedClient.delete(key);
    }

    public Boolean update(String key, Object value) {
        return   cachedClient.replace(key, value);
    }

    public Object get(String key) {
        return cachedClient.get(key);
    }

    public Map gets(String[] list){
        Map map=   cachedClient.getMulti(list);
        return map;
    }


}
