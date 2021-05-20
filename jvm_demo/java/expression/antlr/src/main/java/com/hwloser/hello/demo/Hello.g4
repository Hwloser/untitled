grammar Hello;            // 定义一个名为 Hello 的语法

r  : 'hello' ID ;         // 匹配一个官架子为hello和紧随其后的标识符
ID : [a-z]+ ;             // 匹配小写字母组成的标识符
WS : [ \t\r\n]+ -> skip;  // 忽略 space、TAB、换行、\r(windows)