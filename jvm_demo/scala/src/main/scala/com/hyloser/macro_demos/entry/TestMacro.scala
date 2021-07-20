package com.hyloser.macro_demos.entry

object TestMacro extends App {
  import com.hyloser.macro_demos.define.TestMacroDependency._
  printf("hello %s!", "world")
}
