package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import java.util.HashMap;
import java.util.Map;

public class TestVariable {

  public static void main(String[] args) {
    String expressionStr = "name != nil ? ('hello, ' + name) :'your name is null'";

    Expression exp = AviatorEvaluator.compile(expressionStr);

    Map<String, Object> env = exp.newEnv("name", "huanwei");

    System.out.println(exp.execute(env));;
    System.out.println(exp.execute());;

  }
}
