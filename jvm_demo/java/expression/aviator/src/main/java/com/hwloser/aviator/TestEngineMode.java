package com.hwloser.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Options;

public class TestEngineMode {

  public static void main(String[] args) {
    AviatorEvaluatorInstance compiler = AviatorEvaluator.getInstance();
    compiler.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE);

    

  }
}
