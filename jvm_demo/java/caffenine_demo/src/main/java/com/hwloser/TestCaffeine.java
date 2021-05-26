package com.hwloser;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCaffeine {
  public static void main(String[] args) throws InterruptedException {

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
}
