grammar LabeledExpr;

stat  : expr NEWLINE
      | ID '=' expr NEWLINE   // assign
      | NEWLINE               // blank
      ;


expr  : expr op=('*'|'/') expr  //  Multi or Div
      | expr op=('+'|'-') expr  //  add or sub
      | INT
      | ID
      | '(' expr ')'            // parens
      ;

INT : [0-9]+    ;
ID  : [a-zA-Z]+ ;

NEWLINE : [;];
//NEWLINE : '\r'? '\n';
WS      : [ \t] -> skip;


// difine operator
MUL   : '*' ;       // 为上述使用的 '*' 命名
DIV   : '/' ;
ADD   : '+' ;
SUB   : '-' ;