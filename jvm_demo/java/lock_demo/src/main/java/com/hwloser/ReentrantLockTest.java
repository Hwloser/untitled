package com.hwloser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

  static int count = 0;

  public static void main(String[] args) {
    test_1();
//    test_2();
  }

  private static void test_1() {
    Runnable t1 = () -> {
      while (count < 10000) {
        System.out.println(Thread.currentThread() + " :" + count);
        lockMethod();
      }
    };
    Runnable t2 = () -> {
      while (count < 10000) {
        System.out.println(Thread.currentThread() + " :" + count);
        lockMethod();
      }
    };

    ExecutorService pool = Executors.newFixedThreadPool(2);

    pool.execute(t1);
    pool.execute(t2);

    pool.shutdown();
  }

  private static void test_2() {
    Runnable t1 = () -> {
      while (count < 10000) {
        System.out.println(Thread.currentThread() + " :" + count);
        lockMethod_2();
      }
    };
    Runnable t2 = () -> {
      while (count < 10000) {
        System.out.println(Thread.currentThread() + " :" + count);
        lockMethod_2();
      }
    };

    ExecutorService pool = Executors.newFixedThreadPool(2);

    pool.execute(t1);
    pool.execute(t2);

    pool.shutdown();
  }

  private static final ReentrantLock l = new ReentrantLock();

  private static void lockMethod() {
    l.lock();
    try {
      Thread.sleep(1000);
      System.out.println(l);
      count += 1;
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      l.unlock();
    }
  }

  private synchronized static void lockMethod_2() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    count += 1;
  }
}
