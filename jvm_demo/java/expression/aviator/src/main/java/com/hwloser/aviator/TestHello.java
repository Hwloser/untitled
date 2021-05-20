package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import java.io.IOException;

public class TestHello {

  public static void main(String[] args) throws IOException {
    // Compile the script into a Expression instance
    Expression exp = AviatorEvaluator.getInstance().compileScript("example/hello.av", true);
    // Run the expression
    exp.execute();

    String expStr = "println('hello')";
    Expression exp1 = AviatorEvaluator.getInstance().compile(expStr);

    exp1.execute();
  }
}
