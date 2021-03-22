package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import java.util.Date;
import java.util.HashMap;

public class TestBean {

  public static void main(String[] args) {
    Bean bean = new Bean("huanwei", 21, new Date());

    HashMap<String, Object> env = new HashMap<>();
    env.put("bean", bean);

    Object res1 = AviatorEvaluator.execute("'bean.name = ' + bean.name", env);
    System.out.println(res1);

    String age = "'bean.age = ' + bean.age";
    System.out.println(AviatorEvaluator.execute(age, env));

    String year = "'bean.date.year ---- : ' + (bean.date.year + 1990)";
    System.out.println(AviatorEvaluator.execute(year, env));
  }

  static class Bean {

    String name;
    int age;
    Date date;

    public Bean(String name, int age, Date date) {
      this.name = name;
      this.age = age;
      this.date = date;
    }

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
