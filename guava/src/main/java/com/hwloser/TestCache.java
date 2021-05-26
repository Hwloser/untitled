package com.hwloser;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestCache {
  public static void main(String[] args) throws InterruptedException {
    simpleTest();
    System.out.println(" ----------------------- ");
    setMaximumSize();
    System.out.println(" ----------------------- ");
    setExpireDate();
  }


  private static void simpleTest() {
    Cache<String, String> cache = CacheBuilder.newBuilder().build();
    cache.put("word", "hello guava cache");
    System.out.println(cache.getIfPresent("word"));
  }

  private static void setMaximumSize() {
    Cache<String, String> cache = CacheBuilder.newBuilder()
        .maximumSize(2)
        .build();

    cache.put("key1", "value1");
    cache.put("key2", "value2");
    cache.put("key3", "value3");

    System.out.println("第一个值：" + cache.getIfPresent("key1"));
    System.out.println("第二个值：" + cache.getIfPresent("key2"));
    System.out.println("第三个值：" + cache.getIfPresent("key3"));
  }

  private static void setExpireDate() throws InterruptedException {
    Cache<String, String> cache = CacheBuilder.newBuilder()
        .expireAfterWrite(2, TimeUnit.SECONDS)
        .build();

    cache.put("key1", "value1");
    int time = 4;

    for (int i = 0; i < time; i++) {
      System.out.println("第" + (i + 1) + "次取到key1的值为：" + cache.getIfPresent("key1"));
      Thread.sleep(1000L);
    }
  }

  private void loadingCache() {
    CacheLoader<String, HashMap<String, String>> cache = new CacheLoader<String, HashMap<String, String>>() {
      @Override
      public HashMap<String, String> load(String key) throws Exception {
        Thread.sleep(1000); //休眠1s，模拟加载数据
        System.out.println(key + " is loaded from a cacheLoader!");
        return new HashMap<>();
      }
    };


  }
}
