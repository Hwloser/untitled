package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBigInt;
import com.googlecode.aviator.runtime.type.AviatorObject;
import java.util.Map;

public class TestCustomizeVariadicFunctionArgs {

  public static void main(String[] args) {
    AviatorEvaluator.addFunction(new MultiFunction());
    System.out.println(
        AviatorEvaluator.compile("multi(1,2,3,4,multi)")
            .execute(AviatorEvaluator.newEnv("multi", 30)));
    ;
  }
}

class MultiFunction extends AbstractVariadicFunction {

  @Override
  public String getName() {
    return "multi";
  }

  @Override
  public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
    int res = 1;
    for (AviatorObject arg : args) {
      Number n = FunctionUtils.getNumberValue(arg, env);
      res *= n.intValue();
    }
    return AviatorBigInt.valueOf(res);
  }
}