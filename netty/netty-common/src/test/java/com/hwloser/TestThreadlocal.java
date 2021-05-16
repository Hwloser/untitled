package com.hwloser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TestThreadlocal {
  public static void main(String[] args) {
    test2();
  }

  static void test1() {
    ThreadLocal<Map<String, Object>> m = new ThreadLocal<>();
    if (m.get() == null) {
      HashMap<String, Object> map = new HashMap<>();
      map.put("param", new ArrayList<String>());
      m.set(map);
    }

    System.gc();

    System.out.println(m.get());
  }

  static void test2() {
    ThreadLocal<String> m = new ThreadLocal<>();

    IntStream.range(1, 10).forEach(i -> new Thread(() -> {
      m.set(Thread.currentThread().getName() + i);
      System.out.println("线程：" + Thread.currentThread().getName() + ",local:" + m.get());
    }).start());

    System.out.println("线程：" + Thread.currentThread().getName() + ",local:" + m.get());
  }
}
