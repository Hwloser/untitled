package com.hwloser.calculator;

import com.hwloser.calculator.labeled.LabeledExprLexer;
import com.hwloser.calculator.labeled.LabeledExprParser;
import com.hwloser.calculator.labeled.LabeledExprParser.StatContext;
import java.util.Scanner;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class LabeledExprMain {

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    while (true) {
      String inputExprStr = s.nextLine();
      if ("<EOF>".equals(inputExprStr)) {
        break;
      }

      CodePointCharStream charStream = CharStreams.fromString(inputExprStr);
      LabeledExprLexer lexer = new LabeledExprLexer(charStream);

      CommonTokenStream tokenStream = new CommonTokenStream(lexer);
      LabeledExprParser parser = new LabeledExprParser(tokenStream);
      StatContext stat = parser.stat();
      System.out.println(stat.toStringTree());
    }

  }
}
