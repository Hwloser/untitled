package com.hwloser;

import com.google.common.collect.HashMultiset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class TestMultiSet {

  public static void main(String[] args) {
    multiSetNotEquals();
    multiSetEquals();
    multiSetLittleDiverge();

    multiSetMapEquals();
    multiSetMapNotEquals();
  }

  private static void multiSetMapNotEquals() {
    HashMultiset<Map<Integer, List<String>>> m1 = HashMultiset.create();
    HashMultiset<Map<Integer, List<String>>> m2 = HashMultiset.create();

    Map<Integer, List<String>> rm1 = buildRandomMap();
    Map<Integer, List<String>> rm2 = buildRandomMap();

    Map<Integer, List<String>> rm3 = new HashMap<>();
    List<String> a = new ArrayList<>();
    a.add("test");
    rm1.put(11111, a);

    for (Entry<Integer, List<String>> e : rm1.entrySet()) {
      ArrayList<String> s = new ArrayList<>(e.getValue());
      rm3.put(e.getKey(), s);
    }

    m1.add(rm1);
    m2.add(rm2);

    System.out.println(m1.equals(m2));

    m1.clear();
    m2.clear();

    m1.add(rm1);
    m2.add(rm3);

    System.out.println(m1.equals(m2));

  }

  private static void multiSetMapEquals() {
    HashMultiset<Map<Integer, List<String>>> m1 = HashMultiset.create();
    HashMultiset<Map<Integer, List<String>>> m2 = HashMultiset.create();

    Map<Integer, List<String>> m = buildRandomMap();

    m1.add(m);
    m2.add(m);

    System.out.println(m1.equals(m2));
  }

  private static void multiSetNotEquals() {
    HashMultiset<List<String>> m1 = HashMultiset.create();
    HashMultiset<List<String>> m2 = HashMultiset.create();

    m1.add(buildRandomList());
    m2.add(buildRandomList());

    System.out.println(m1.equals(m2));
  }

  private static void multiSetEquals() {
    HashMultiset<List<String>> m1 = HashMultiset.create();
    HashMultiset<List<String>> m2 = HashMultiset.create();

    List<String> e = buildRandomList();

    m1.add(e);
    m2.add(e);

    System.out.println(m1.equals(m2));
  }

  private static void multiSetLittleDiverge() {
    HashMultiset<List<String>> m1 = HashMultiset.create();
    HashMultiset<List<String>> m2 = HashMultiset.create();

    List<String> e = buildRandomList();
    List<String> e2 = new ArrayList<>(e);

    m1.add(e);
    m2.add(e2);

    System.out.println(m1.equals(m2));
  }

  private static Map<Integer, List<String>> buildRandomMap() {
    Random r = new Random();
    Map<Integer, List<String>> map = new HashMap<>();
    for (int i = 0; i < 128; i++) {
      map.put(r.nextInt(1000), buildRandomList());
    }
    return map;
  }

  private static List<String> buildRandomList() {
    ArrayList<String> list = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      list.add(randomString());
    }
    return list;
  }

  private static String randomString() {
    Random r = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 128; i++) {
      sb.append((char) r.nextInt(128));
    }
    return sb.toString();
  }
}
