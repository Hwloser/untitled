package com.hyloser

object TestCaseMatch {
  def main(args: Array[String]): Unit = {
    val o: Option[Int] = Option(1)

    o match {
      case Some(value) => println(value)
      case None =>
    }

    o match {
      case x @ Some(_) => println(x)
    }

  }
}
