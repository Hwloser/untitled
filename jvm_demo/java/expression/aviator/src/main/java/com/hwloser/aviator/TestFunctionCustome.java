package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import java.util.Map;

public class TestFunctionCustome {

  public static void main(String[] args) {
    // 注册函数
    AviatorEvaluator.addFunction(new AddFunction());
    System.out.println(AviatorEvaluator.execute("add(1,2)"));
    System.out.println(AviatorEvaluator.execute("add(add(33,1),22)"));
  }
}

class AddFunction extends AbstractFunction {

  @Override
  public AviatorObject call(Map<String, Object> env,
      AviatorObject arg1, AviatorObject arg2) {
    Number left = FunctionUtils.getNumberValue(arg1, env);
    Number right = FunctionUtils.getNumberValue(arg2, env);
    return new AviatorDouble(left.doubleValue() + right.doubleValue());
  }

  @Override
  public String getName() {
    return "add";
  }
}