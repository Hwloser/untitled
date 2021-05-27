package com.hwloser;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class TestCaffeine {
  public static void main(String[] args) throws InterruptedException {
    //    simpleTestCache();

    testSecondCache();
  }

  private static void testSecondCache() throws InterruptedException {
    ConcurrentHashMap<Integer, WeakReference<Integer>> secondCacheMap =
        new ConcurrentHashMap<>();

    LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
        .maximumSize(1)
        .writer(new CacheWriter<Integer, Integer>() {
          @Override
          public void write(@NonNull Integer key, @NonNull Integer value) {
            secondCacheMap.put(key, new WeakReference<>(value));
            System.out.println("触发CacheWriter.write，将key = " + key + "放入二级缓存中");
          }
          @Override
          public void delete(@NonNull Integer key, @Nullable Integer value,
              @NonNull RemovalCause cause) {
            switch (cause) {
              case EXPIRED: {
                secondCacheMap.remove(key);
                System.out.println("触发CacheWriter" +
                    ".delete，清除原因：主动清除，将key = " + key +
                    "从二级缓存清除");
                break;
              }
              case SIZE: {
                System.out.println("触发CacheWriter" +
                    ".delete，清除原因：缓存个数超过上限，key = " + key);
                break;
              }
              default:
                break;
            }
          }
        })
        .build(new CacheLoader<Integer, Integer>() {
          @Override
          public @Nullable Integer load(@NonNull Integer key) throws Exception {
            WeakReference<Integer> value = secondCacheMap.get(key);
            if (value == null) {
              System.out.println("value is null");
              return null;
            }

            System.out.println("触发CacheLoader.load，从二级缓存读取key = " + key);
            return value.get();
          }
        });

    cache.put(1, 1);
    cache.put(2, 2);
    // 由于清除缓存是异步的，因而睡眠1秒等待清除完成
    Thread.sleep(1000);

    // 缓存超上限触发清除后
    System.out.println("从Caffeine中get数据，key为1，value为" + cache.get(1));
  }


  private void testWeakKeysMap() {
    AtomicInteger a = new AtomicInteger();
    Cache<Object, Object> cache = Caffeine.newBuilder()
        .initialCapacity(128)
        .weakKeys()
        .expireAfterWrite(2, TimeUnit.SECONDS)
        .build();

    Object c1 = cache.get("i", k -> k + "" + a.incrementAndGet());

    System.gc(); /* breakpoint gc, or trigger gc explicitly */

    Object c2 = cache.get("i", k -> k + "" + a.incrementAndGet());

    System.out.println(c1);
    System.out.println(c2);
  }

  private static void simpleTestCache() throws InterruptedException {

    AtomicInteger a = new AtomicInteger();

    Cache<Object, Object> cache = Caffeine.newBuilder()
        .initialCapacity(128)
        .expireAfterWrite(2, TimeUnit.SECONDS)
        .build();

    for (int i = 0; i < 8; i++) {
      Object c = cache.get(i, k -> k + "" + a.incrementAndGet());
      System.out.println(c);

      System.gc();

      Thread.sleep(TimeUnit.SECONDS.toMillis(1));

      Object c1 = cache.get(i, k -> k + "" + a.incrementAndGet());
      System.out.println(c1);

      Thread.sleep(TimeUnit.SECONDS.toMillis(1));
    }
  }
}
