package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import java.util.Map;

public class TestRelationalOperator {

  public static void main(String[] args) {
    String expression = "a-(b-c) > 100";

    AviatorEvaluatorInstance compiler = AviatorEvaluator.getInstance();
    // Execute with injected variables
    Expression exp = compiler.compile(expression);

    Map<String, Object> env = exp.newEnv(
        "a", 100.3,
        "b", 45,
        "c", 20
    );

    Boolean res = (Boolean) exp.execute(env);
    System.out.println(res);

    /*
    * execute process
    * a-(b-c) > 100
    * => 100.3 - (45 - -199.100) > 100
    * => 100.3 - 244.1 > 100
    * => -143.8 > 100
    * => false
    * */
  }
}
