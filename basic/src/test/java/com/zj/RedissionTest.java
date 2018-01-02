package com.zj;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/18
 * Time: 20:41
 * CopyRight: Zhouji
 */
public class RedissionTest {
    @Test
    public void redis() {
        Config config = new Config();
//        config.setUseLinuxNativeEpoll(true);
//        config.useClusterServers()
                // use "rediss://" for SSL connection
//                .addNodeAddress("redis://127.0.0.1:6379");
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        try {
            System.out.println(redisson.getConfig().toJSON().toString());

            RAtomicLong longObject = redisson.getAtomicLong("mykey");
            longObject.compareAndSet(3, 401);
            longObject.compareAndSetAsync(3, 401);

            RedissonReactiveClient client = Redisson.createReactive(config);
// reactive way
            longObject.compareAndSet(3, 401);

            RLock lock = redisson.getLock("anyLock");
            lock.lock();
            // 支持过期解锁功能 10秒钟以后自动解锁
            // 无需调用unlock方法手动解锁
            lock.lock(10, TimeUnit.SECONDS);
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
            lock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RedissonClient client = Redisson.create(config);
        client.createBatch();
    }
}
