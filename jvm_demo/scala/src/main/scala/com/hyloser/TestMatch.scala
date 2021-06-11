package com.hyloser

object TestMatch {
  def main(args: Array[String]): Unit = {
    Array("AB") foreach {
      case s if s.contains("A") => println("A")
      case s if s.contains("B") => println("B")
      case _ => println("default")
    }
  }

}
