package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestArgs {

  public static void main(String[] a) throws IOException {
    AviatorEvaluatorInstance compiler = AviatorEvaluator.getInstance();

    Expression exp = compiler.compileScript("example/test_args.av");

    HashMap<String, Object> env = new HashMap<>();
    ArrayList<String> args = new ArrayList<>();
    args.add("test");
    env.put("ARGV", args);

    exp.execute(env);
  }
}
