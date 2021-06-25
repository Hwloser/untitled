package com.hwloser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class TestCurator {

  public static void main(String[] args) {
    CuratorFramework zk = getClient();

    String lockPath = "/lock";

    InterProcessMutex lock = new InterProcessMutex(zk, lockPath);

    // 线程争夺

    ExecutorService pool = Executors.newFixedThreadPool(50);

    for (int i = 0; i < 50; i++) {
      int finalI = i;
      pool.execute(() -> {
        try {
          lock.acquire();
          System.out.println("第" + finalI + "线程获取到了锁");
          Thread.sleep(1000);
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try {
            lock.release();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
    }

    pool.shutdown();
  }


  private static CuratorFramework getClient() {
    String qurom = "archlinux:2181";
    int baseSleepTimeMs = 1000;
    int maxRetries = 3;

    int sessionTimeoutMs = 5000;
    int connectionTimeoutMs = 5000;

    ExponentialBackoffRetry r = new ExponentialBackoffRetry(
        baseSleepTimeMs,
        maxRetries
    );

    CuratorFramework c = CuratorFrameworkFactory
        .builder()
        .connectString(qurom)
        .sessionTimeoutMs(sessionTimeoutMs)
        .connectionTimeoutMs(connectionTimeoutMs)
        .retryPolicy(r)
        .build();

    c.start();
    return c;
  }
}
