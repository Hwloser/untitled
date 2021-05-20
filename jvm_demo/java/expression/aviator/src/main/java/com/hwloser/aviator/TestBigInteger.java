package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import java.io.IOException;

public class TestBigInteger {

  public static void main(String[] args) throws IOException {
    Expression expression = AviatorEvaluator.getInstance().compileScript("example/bigint.av");

    expression.execute();

  }
}
