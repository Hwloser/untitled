package com.hwloser.thread.and;

public class And {

  public static void main(String[] args) {
    int threshold = Integer.parseInt(args[0]);
    System.out.println((threshold - 1) & 7);  // similar % threshold
  }
}
