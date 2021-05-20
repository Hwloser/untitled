package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.lexer.token.OperatorType;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorType;
import java.util.Map;

public class CustomDivideExample {

  public static void main(String[] args) {

    Double throwableExceptionRres = (Double) AviatorEvaluator.execute("1 / 2");
    System.out.println(throwableExceptionRres);

    // 上述由于未转化 分子为浮点数，div 的时候会抛出异常

    /*
    * Exception in thread "main" java.lang.ClassCastException:
    *   java.lang.Long cannot be cast to java.lang.Double
	  *     at com.hwloser.aviator.CustomDivideExample.main(
	  *       CustomDivideExample.java:40)
    * */

    AviatorEvaluator.addOpFunction(OperatorType.DIV, new AbstractFunction() {
      @Override
      public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        if (arg1.getAviatorType() == AviatorType.Long &&
            arg2.getAviatorType() == AviatorType.Long) {
          // if arg1 and arg2 are both long type
          // cast arg2 into double and divided by arg1

          long castArgs1 = FunctionUtils.getNumberValue(arg1, env).longValue();
          double castArgs2 = FunctionUtils.getNumberValue(arg2, env).doubleValue();

          double res = castArgs1 / castArgs2;
          return AviatorDouble.valueOf(res);
        } else {
          arg1.div(arg2, env);
        }
        return super.call(env, arg1, arg2);
      }

      @Override
      public String getName() {
        return OperatorType.DIV.getToken();
      }
    });
    Double res = (Double) AviatorEvaluator.execute("1 / 2");
    System.out.println(res);
  }
}
