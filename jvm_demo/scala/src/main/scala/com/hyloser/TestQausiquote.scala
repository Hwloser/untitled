package com.hyloser

import scala.reflect.runtime.universe.Quasiquote

object TestQausiquote {

  def main(args: Array[String]): Unit = {
    val tree = q"i am { a quasiquote }"

    print(tree)
  }
}
