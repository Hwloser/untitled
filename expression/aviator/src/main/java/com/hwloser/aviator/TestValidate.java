package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;

public class TestValidate {

  public static void main(String[] args) {
//    AviatorEvaluatorInstance compiler = AviatorEvaluator.getInstance();

    String expression1 = "1 + 1";
    String expression2 = "1 +* 1";

    AviatorEvaluator.validate(expression1);

    System.out.println("expression1 validate end");

    AviatorEvaluator.validate(expression2);
    System.out.println("expression2 validate end");
  }
}
