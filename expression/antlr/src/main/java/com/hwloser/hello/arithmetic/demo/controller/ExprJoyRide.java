package com.hwloser.hello.arithmetic.demo.controller;

import com.hwloser.hello.arithmetic.easy.prog.ExprLexer;
import com.hwloser.hello.arithmetic.easy.prog.ExprParser;
import com.hwloser.hello.arithmetic.easy.prog.ExprParser.ProgContext;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class ExprJoyRide {

  public static void main(String[] args) {
    String input = "1 * 1 \n  aa \n";

    CodePointCharStream chars = CharStreams.fromString(input);
    ExprLexer lexer = new ExprLexer(chars);

    CommonTokenStream tokens = new CommonTokenStream(lexer);
    ExprParser parser = new ExprParser(tokens);

    ProgContext tree = parser.prog();

    System.out.println(tree.toStringTree(parser));
  }
}
