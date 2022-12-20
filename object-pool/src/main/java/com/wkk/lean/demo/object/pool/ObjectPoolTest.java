package com.wkk.lean.demo.object.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;

/**
 * @author Wangkunkun
 * @date 2022/12/17 20:40
 */
public class ObjectPoolTest {

    public static final Object object = new Object();

    public static void main(String[] args) throws Exception {
        // 声明对象池
        GenericObjectPoolConfig<Client> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxTotal(20);
        poolConfig.setMinIdle(5);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setTestOnCreate(true);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setMinEvictableIdleTime(Duration.ofSeconds(20));
        poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(20));
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setMaxWait(Duration.ofSeconds(20));
        poolConfig.setJmxEnabled(false);
        ClientObjectPool clientObjectPool = new ClientObjectPool(new ClientObjectFactory(), poolConfig);
        // 池中借用对象
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Client client = null;
                try {
                    client = clientObjectPool.borrowObject();
                    client.printlnInfo();
                    System.out.println("Idle=" + clientObjectPool.getNumIdle() + "；Active=" + clientObjectPool.getNumActive());
                    Thread.sleep(1000);
                    // 使用对象
                    // 归还给对象池
                    clientObjectPool.returnObject(client);
                    System.out.println("Idle=" + clientObjectPool.getNumIdle() + "；Active=" + clientObjectPool.getNumActive());
                    // 查看对象池
                    System.out.println(clientObjectPool.listAllObjects());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();

        }

        Thread.sleep(60 * 1000);
    }
}
