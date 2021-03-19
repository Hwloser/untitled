package com.hwloser.aviator;

import java.util.Date;

public class TestBean {

  public static void main(String[] args) {

  }

  static class Bean {
    String name;
    int age;
    Date date;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public Date getDate() {
      return date;
    }

    public void setDate(Date date) {
      this.date = date;
    }
  }
}
