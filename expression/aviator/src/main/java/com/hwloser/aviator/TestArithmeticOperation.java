package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import java.io.IOException;

public class TestArithmeticOperation {

  public static void main(String[] args) throws IOException {
    AviatorEvaluatorInstance compiler = AviatorEvaluator.getInstance();

    Expression expression = compiler.compileScript("example/arithmetic_operation.av");

    expression.execute(expression.newEnv("d", 20));

    System.out.println(" -------------------------- ");

    expression.execute(expression.newEnv("d", 20, "c", 100000));
  }
}
