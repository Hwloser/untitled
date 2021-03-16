package com.hwloser.thread.context;

public class ContextThreadLoader {
  public static void main(String[] args) {
    ClassLoader cc = Thread.currentThread().getContextClassLoader();
    System.out.println(cc);
    System.out.println(cc.getParent());
    System.out.println(cc.getParent().getParent());
  }
}
