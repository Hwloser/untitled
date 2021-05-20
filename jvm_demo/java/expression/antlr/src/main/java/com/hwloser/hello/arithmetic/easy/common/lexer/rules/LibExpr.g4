grammar LibExpr;            // rename prog/Expr.g4

import CommonLexerRules;    // import CommonLexerRules.g4 中的全部规则

prog : stat+ ;

stat  : expr NEWLINE
      | NEWLINE
      ;

expr  : ID
      | INT
      | '(' expr ')'
      | expr ('*'|'/') expr
      | expr ('+'|'/') expr
      ;

