package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import java.io.IOException;

public class TestStaticVariables {

  public static void main(String[] args) throws IOException {
    Expression exp = AviatorEvaluator.getInstance().compileScript("example/static_variables.av");
    exp.execute();
  }
}
