grammar Calc;

stat: expr EOF?;

expr:  SUB expr                          # minus
    | expr op=(MUL | DIV | MOD) expr     # mul_div_mod
    | expr op=(ADD | SUB) expr           # add_sub
    | INT                                # int
    | '(' expr ')'                       # paren
    ;

ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
MOD : '%';

INT :   [0-9]+  ;
WS  :   [ \t\r\n]+  -> skip;

